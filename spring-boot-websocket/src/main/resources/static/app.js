var ws;
function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
}

function connect() {
	ws = new WebSocket('ws://localhost:8002/user');
	ws.onmessage = function(data) {
		helloWorld(data.data);
		//
        // if(typeof event.data === String) {
        //     console.log("Received data string");
        // }
        //
        // if(event.data instanceof ArrayBuffer){
        //     var buffer = event.data;
        //     console.log("Received arraybuffer");
        // }
	}
    ws.onopen = function(event) {
        console.log("on open:", event);
    }
    ws.onclose = function(event) {
        console.log("on close:", event);
    }
    ws.onerror = function(event) {
        console.log("on error:", event);
    }
    // // 0 (CONNECTING)/1 (OPEN)/2 (CLOSING)/3 (CLOSED)
    // var readyState = ws.readyState;
	setConnected(true);
}

function disconnect() {
	if (ws != null) {
		ws.close();
	}
	setConnected(false);
	console.log("Websocket is in disconnected state");
}

function sendData() {
	var data = JSON.stringify({
		'user' : $("#user").val()
	})
	ws.send(data);
}

function helloWorld(message) {
	$("#helloworldmessage").append("<tr><td> " + message + "</td></tr>");
}

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(function() {
		connect();
	});
	$("#disconnect").click(function() {
		disconnect();
	});
	$("#send").click(function() {
		sendData();
	});
});
