$(document).ready(function() {
    let id=window.localStorage.getItem("Id");
    $.ajax({
        type: "GET",
        url: "http://localhost:8090/api/korisnici/odradjeniTreninzi/"+id,
        dataType: "json",
        success: function(response) {
            console.log("USPESNO", response);
            console.log(response);

            for(let trening of response){
                let row = "<tr>";
                row+= "<td>" + trening.id + "</td>";
                row+= "<td>" + trening.naziv + "</td>";
                row+= "<td>" + trening.tipTreninga + "</td>";
                row+= "<td>" + trening.opis + "</td>";
                row+= "<td>" + trening.vreme + "</td>";
                row+= "<td>" + trening.oznakaSale + "</td>";
                row+= "<td>" + trening.trajanje + "</td>";
                row+= "<td>" + trening.cena + "</td>";
                row+= "<td>" + trening.brojPrijavljenihClanova + "</td>";
                row+= "<td>" + trening.imeTrenera + "</td>";
                row+= "<td>" + trening.prezimeTrenera + "</td>";
                row+="</tr>";

                $('.content-table').append(row);
            }
        },
        error: function(response) {
            console.log("ERROR: ", response);
        }
    });

});