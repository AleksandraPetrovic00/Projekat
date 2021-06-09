$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8090/api/fitnesCentri",
        dataType: "json",
        success: function(response) {
            console.log("USPESNO", response);
            console.log(response);

            for(let fitnesCentar of response){
                let row = "<tr>";
                row+= "<td>" + fitnesCentar.id + "</td>";
                row+= "<td>" + fitnesCentar.naziv + "</td>";
                row+= "<td>" + fitnesCentar.adresa + "</td>";
                row+= "<td>" + fitnesCentar.brojTelefonaCentrale + "</td>";
                row+= "<td>" + fitnesCentar.email + "</td>";
                row+="</tr>";

                $('.content-table').append(row);
            }
        },
        error: function(response) {
            console.log("ERROR: ", response);
        }
    });

});