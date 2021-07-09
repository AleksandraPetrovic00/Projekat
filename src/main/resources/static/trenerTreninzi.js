$(document).ready(function() {
    let id = window.localStorage.getItem("Id");
    $.ajax({
        type: "GET",
        url: "http://localhost:8090/api/treninzi/trener/"+id,
        dataType: "json",
        success: function(response) {
            console.log("USPESNO", response);
            console.log(response);

            for(let trening of response){
                let row = "<tr>";
                row+= "<td>" + trening.id + "</td>";
                row+= "<td>" + trening.naziv + "</td>";
                row+= "<td>" + trening.opis + "</td>";
                row+= "<td>" + trening.tipTreninga + "</td>";
                row+= "<td>" + trening.trajanje + "</td>";
                row+="<td><button class='izmeni' onClick=window.location.href='izmeniTrening.html' data-id='"+trening.id+"'  >IZMENI</button></td>";
                row+="</tr>";

                $('.content-table').append(row);
            }
        },
        error: function(response) {
            console.log("ERROR: ", response);
        }
    });

    $(document).on('click', '.izmeni', function(){
        let treningId = this.dataset.id;
        console.log(treningId);
        localStorage.setItem("treningId", treningId);
    })


});