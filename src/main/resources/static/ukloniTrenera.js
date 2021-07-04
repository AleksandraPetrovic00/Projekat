$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8090/api/korisnici/treneri",
        dataType: "json",
        success: function(response) {
            console.log("USPESNO", response);
            console.log(response);

            for(let korisnik of response){
                let row = "<tr>";
                row+= "<td>" + korisnik.id + "</td>";
                row+="<td>" + korisnik.aktivan + "</td>";
                row+= "<td>" + korisnik.korisnickoIme + "</td>";
                row+= "<td>" + korisnik.lozinka + "</td>";
                row+= "<td>" + korisnik.ime + "</td>";
                row+= "<td>" + korisnik.prezime + "</td>";
                row+= "<td>" + korisnik.kontaktTelefon + "</td>";
                row+= "<td>" + korisnik.emailAdresa + "</td>";
                row+= "<td>" + korisnik.datumRodjenja + "</td>";
                row+="<td><input type='checkbox' data='"+korisnik.id+"'/></td>"
                row+="</tr>";

                $('.content-table').append(row);
            }
        },
        error: function(response) {
            console.log("ERROR: ", response);
        }
    });
$("#potvrdi").on("click", function(event){
    event.preventDefault();
    let ids=[]
    $("[type='checkbox']").each(function(elem){
        console.log(this)
        if($(this).is(":checked")){
            ids.push($(this).attr("data"));
            window.location.href="ukloniTrenera.html"
        }
    })
        let duzina=ids.length;
        for(var i=0; i<duzina; i++)
        {
            $.ajax({
                type:"DELETE",
                url:"http://localhost:8090/api/korisnici/"+ids[i],
                sataType:"json",
                contentType: "application/json",
                data: JSON.stringify(ids)
            
            });
        }
})

});