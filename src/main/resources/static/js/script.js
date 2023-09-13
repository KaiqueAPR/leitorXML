var inputFiles = [];

function newInput(input) {
    var filesStr = "";

    for (let i = 0; i < input.files.length; i++) {
        inputFiles.push(input.files[i]);

        filesStr += "<li>" + "<i class='fa-solid fa-file'></i>" + input.files[i].name + "<button id='btnRemover' onclick='removeLi(this)'>Remover</button>" + "</li>";
    }

    document.getElementById("file-input").value = "";

    document.getElementById("dp-files").innerHTML += filesStr;
}

function removeLi(e) {
    inputFiles = inputFiles.filter(function (file) {
        return file.name !== e.parentNode.innerHTML.split("<button")[0];
    })
    e.parentNode.parentNode.removeChild(e.parentNode);
}