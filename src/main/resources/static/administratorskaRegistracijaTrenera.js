$(document).ready(function (event) {

    let newUpit = JSON.parse(window.localStorage.getItem("korisnik"));
    $.ajax({
        type: "POST",
        url: "http://localhost:8090/pristup",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newUpit),
        success: function (response) {
            if (response.uloga === "ADMINISTRATOR") {
            }
            else {
                alert("Nemate pristup ovoj stranici!");
                window.location.href = "login.html";
            }
        },
        error: function (response) {
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "login.html";
        }
    });

});

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
                row+= "<td>" + fitnesCentar.naziv + "</td>";
                row+= "<td><input type='checkbox' data='"+fitnesCentar.id+"'></td>"
                row+="</tr>";

                $('.content-table').append(row);
            }
        },
        error: function(response) {
            console.log("ERROR: ", response);
        }

    });
})

$("#coachRegistration").on("click", function(event){
    event.preventDefault();

    let ids=[]
        $("[type='checkbox']").each(function(elem){
            console.log(this)
            if($(this).is(":checked")){
                ids.push($(this).attr("data"));
            }
        })

    let ime = $("#ime_reg").val();
    let prezime = $("#prezime_reg").val();
    let korisnickoIme = $("#korisnickoIme_reg").val();
    let lozinka = $("#lozinka_reg").val();
    let emailAdresa = $("#emailAdresa_reg").val();
    let kontaktTelefon = $("#kontaktTelefon_reg").val();
    let datumRodjenja = $("#datumRodjenja_reg").val();


    let newTrener = {
        korisnickoIme,
        lozinka,
        ime,
        prezime,
        kontaktTelefon,
        emailAdresa,
        datumRodjenja,
        uloga: "TRENER",
        ids
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:8090/api/korisnici/administratorskaRegistracija/"+ids,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newTrener),
        success: function(response){
            console.log(response);

            alert("Trener " +response.id + " je dodat");
            window.location.href = "administratorskaRegistracijaTrenera.html";

        },
        error:function(e){
            alert("Greska prilikom registracije");
        }


    });

});

