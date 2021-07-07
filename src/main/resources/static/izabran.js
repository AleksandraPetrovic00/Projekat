$(document).ready(function() {
    let id=window.localStorage.getItem("terminId");
    $.ajax({
        type: "GET",
        url: "http://localhost:8090/api/termini/"+id,
        dataType: "json",
        success: function(response) {
            console.log("USPESNO", response);
            console.log(response);

            let trening = response;
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
                row+="<td><button class='prijavi' data-id='"+trening.id+"' onClick=window.location.href='prijavljeniTreninzi.html'>PRIJAVI</button></td>";
                row+="</tr>";

                $('.content-table').append(row);

        },
        error: function(response) {
            console.log("ERROR: ", response);
        }
    });

    $(document).on('click', '.prijavi', function(){
        let idKorisnika = window.localStorage.getItem("Id");
        console.log(idKorisnika);
        let idTermin = window.localStorage.getItem("terminId");
        $.ajax({
        type: "GET",
        url: "http://localhost:8090/api/korisnici/prijaviTrening/"+idKorisnika+"/"+idTermin,
        dataType: "json",
        success: function(response) {
            console.log("USPESNO", response);
            console.log(response);

        },
        error: function(response) {
            console.log("ERROR: ", response);
        }
    });
    })

});