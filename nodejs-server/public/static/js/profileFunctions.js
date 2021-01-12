
function runCrawler() {

    if (validate()) {
        const xhr = new XMLHttpRequest()

        xhr.open('POST', '/api/crawler-run')
        xhr.setRequestHeader('Content-Type', 'application/json')

        xhr.onload = () => {
            if (xhr.status >= 400) {
                document.getElementById('validationMessage').innerHTML = 'need to check the link';
                xhr.response
            } else {
                if (xhr.responseText == 'Article exist already') {
                    document.getElementById('validationMessage').innerHTML = 'Article with link: ' + document.getElementById('link').value + ' already exists';
                } else {

                }
            }
        }
        xhr.onerror = () => {
            document.getElementById('validationMessage').innerHTML = 'need to check the link';
            xhr.response
        }
        xhr.send(JSON.stringify({ "seed": document.getElementById('seed').value, "terms": document.getElementById('terms').value }))
    }
}

function validate() {
    var regLink = /^(http).*/;
    var link = document.getElementById('seed').value;
    if (regLink.test(link) == false) {
        document.getElementById('validationMessage').innerHTML = 'enter the correct link';
        return false;
    } else return true;
}

document.addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        runCrawler();
        afterRunningMessage();
    }
})

document.getElementById("run").addEventListener("click", function() {
    runCrawler();
    afterRunningMessage();
  });

function dounloadCSV(fileName) {
    const xhr = new XMLHttpRequest()

        xhr.open('GET', '/api/download/' + fileName)
        xhr.setRequestHeader('Content-Type', 'application/json')

        xhr.responseType = 'blob';

        xhr.onload = () => {
            if (xhr.status >= 400) {
                xhr.response;
            } 
        }
        xhr.onerror = () => {
            xhr.response;
        }
        xhr.send();
}

function afterRunningMessage() {
    document.getElementById("afterRun").style.display = "block";
    document.getElementById("mainBlock").style.display = "none";
}
