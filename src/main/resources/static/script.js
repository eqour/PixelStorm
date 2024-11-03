const socket = new SockJS('/canvas-websocket');
const stompClient = Stomp.over(socket);
stompClient.debug = false;

const canvas = document.getElementById('pixelCanvas');
const ctx = canvas.getContext('2d');
const colorPicker = document.getElementById('colorPicker');
const colorSelector = document.querySelector('.color-selector');

const canvasSize = { width: 10, height: 10 };
const displaySize = { width: 700, height: 700 };

function updateCanvasSize(size) {
    canvasSize.width = size;
    canvasSize.height = size;
    canvas.width = canvasSize.width;
    canvas.height = canvasSize.height;
    canvas.style.width = `${displaySize.width}px`;
    canvas.style.height = `${displaySize.height}px`;
}

function updateCanvas(data) {
    for (let y = 0; y < canvasSize.height; y++) {
        for (let x = 0; x < canvasSize.width; x++) {
            const color = data[y][x];
            ctx.fillStyle = color;
            ctx.fillRect(x, y, 1, 1);
        }
    }
}

function getPixelCoordinates(event) {
    const rect = canvas.getBoundingClientRect();
    const x = Math.floor((event.clientX - rect.left) / (displaySize.width / canvasSize.width));
    const y = Math.floor((event.clientY - rect.top) / (displaySize.height / canvasSize.height));
    return { x, y };
}

function handleUpdatePixel(x, y, color) {
    const requestData = {
        x: x,
        y: y,
        color: color
    }
    stompClient.send('/app/update-pixel', {}, JSON.stringify(requestData));
}

let isDrawing = false;

canvas.addEventListener('mousedown', (event) => {
    isDrawing = true;
    const { x, y } = getPixelCoordinates(event);
    const color = colorPicker.value;
    handleUpdatePixel(x, y, color);
});

canvas.addEventListener('mousemove', (event) => {
    if (isDrawing) {
        const { x, y } = getPixelCoordinates(event);
        const color = colorPicker.value;
        handleUpdatePixel(x, y, color);
    }
});

canvas.addEventListener('mouseup', () => {
    isDrawing = false;
});

colorPicker.addEventListener('input', (event) => {
    colorSelector.style.backgroundColor = event.target.value;
});

colorSelector.style.backgroundColor = colorPicker.value;

stompClient.connect({}, (frame) => {
    stompClient.subscribe('/canvas-change', (message) => {
        updateCanvas(JSON.parse(message.body));
    });

    fetch('/canvas')
    .then(response => {
        return response.json();
    })
    .then(info => {
        updateCanvasSize(info.size);
        updateCanvas(info.data);
    })
    .catch(error => {
        console.error('fetch error:', error)
    });
});
