// url 使用相对路径时前面会自动加上 servletContext
ajax("GET", "goods", null, callback)
ajax("GET", "goods/1", null, callback)
ajax("POST", "goods", JSON.stringify({"id": 1, "name": "拖鞋", "price": 20}), callback)
ajax("PUT", "goods/1", JSON.stringify({"id": 1, "name": "烤翅", "price": 10}), callback)
ajax("DELETE", "goods/1", null, callback)

function callback(responseText)
{
    console.log(responseText)
}

function ajax(method, url, data, callback)
{
    const xhr = new XMLHttpRequest()
    xhr.open(method, url, true)
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8")
    xhr.send(data)
    xhr.addEventListener("readystatechange", function() {
        if (this.readyState === 4)
        {
            callback(this.responseText)
        }
    })
}
