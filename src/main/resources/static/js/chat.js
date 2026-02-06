    const protocol = location.protocol === "https:" ? "wss" : "ws";
    const ws = new WebSocket(`${protocol}://${location.host}/chat/ws`);
    const chat = document.getElementById("chat");

    ws.onmessage = e => {
      const div = document.createElement("div");
      div.textContent = e.data;
      chat.appendChild(div);
      chat.scrollTop = chat.scrollHeight;
    };

    function send() {
      const input = document.getElementById("msg");
      ws.send(input.value);
      input.value = "";
    }