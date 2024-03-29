$(document).ready(function() {
    let id=window.localStorage.getItem("Id");
    $.ajax({
        type: "GET",
        url: "http://localhost:8090/api/korisnici/prijavljeniTermini/"+id,
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
                row+="<td><button class='odjavi' data-id='"+trening.id+"' onClick=window.location.href='prijavljeniTreninzi.html'>ODJAVI</button></td>";
                row+="</tr>";

                $('.content-table').append(row);
            }
        },
        error: function(response) {
            console.log("ERROR: ", response);
        }
    });

    $(document).on('click', '.odjavi', function(){
        let idKorisnika = window.localStorage.getItem("Id");
        console.log(idKorisnika);
        let idTermin = this.dataset.id;
        console.log(idTermin);
        $.ajax({
        type: "GET",
        url: "http://localhost:8090/api/korisnici/odjaviTrening/"+idKorisnika+"/"+idTermin,
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