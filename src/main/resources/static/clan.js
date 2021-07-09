$(document).ready(function (event) {

    let newUpit = JSON.parse(window.localStorage.getItem("korisnik"));
    $.ajax({
        type: "POST",
        url: "http://localhost:8090/pristup",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newUpit),
        success: function (response) {
            if (response.uloga === "CLAN") {
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

$(document).ready(function(){
    let uloga = window.localStorage.getItem("Uloga");
    let id = window.localStorage.getItem("Id");
    $.ajax({
        type: "GET",
        url: "http://localhost:8090/api/korisnici/"+id,
        dataType: "json",
        success: function(response) {
            console.log("USPESNO", response);
            console.log(response);

            let korisnik = response;
                let row = "<tr>";
                row+= "<td>" + korisnik.id + "</td>";
                row+= "<td>" + korisnik.korisnickoIme + "</td>";
                row+= "<td>" + korisnik.ime + "</td>";
                row+= "<td>" + korisnik.prezime + "</td>";
                row+= "<td>" + korisnik.kontaktTelefon + "</td>";
                row+= "<td>" + korisnik.emailAdresa + "</td>";
                row+= "<td>" + korisnik.datumRodjenja + "</td>";
                row+= "<td>" + korisnik.uloga + "</td>";
                row+= "<td>" + korisnik.aktivan + "</td>";
                row+="</tr>";

                $('.content-table').append(row);
        },
        error: function(response) {
            console.log("ERROR: ", response);
        }
    });
})

$("#singOut").on("click", function(event){
    event.preventDefault();

    window.localStorage.removeItem("Id");
    window.localStorage.removeItem("Uloga");
    window.localStorage.removeItem("korisnik");

    window.location.href="login.html";

})