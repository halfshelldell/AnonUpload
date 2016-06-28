/**
 * Created by illladell on 6/27/16.
 */
function getFiles(data) {
    for (var i in data) {
        var elem = $("<a>");
        elem.attr("href", "files/" + data[i].realFileName);
        elem.text(data[i].comment);
        $("#list").append(elem);
        $("#list").append($("<br>"));
    }
}


$.get("/files", getFiles);
