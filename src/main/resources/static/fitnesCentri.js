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
                row+="<td><button class='izmeni' onClick=window.location.href='editFitnesCentar.html' data-id='"+fitnesCentar.id+"'  >EDIT</button></td>";
                row+= "<td><input type='checkbox' data='"+fitnesCentar.id+"'></td>"
                row+="</tr>";

                $('.content-table').append(row);
            }
        },
        error: function(response) {
            console.log("ERROR: ", response);
        }

    });

    $(document).on('click', '.izmeni', function(){
        let fitnesCentarId = this.dataset.id;
        console.log(fitnesCentarId);
        localStorage.setItem("centarId", fitnesCentarId);
    })


    $("#potvrdi").on("click", function(event){
        event.preventDefault();
        let ids=[]
        $("[type='checkbox']").each(function(elem){
            console.log(this)
            if($(this).is(":checked")){
                ids.push($(this).attr("data"));
                window.location.href="fitnesCentri.html"
            }
        })
        let duzina=ids.length;
        for(var i=0; i<duzina; i++)
        {
            $.ajax({
                type:"DELETE",
                url:"http://localhost:8090/api/fitnesCentri/"+ids[i],
                sataType:"json",
                contentType: "application/json",
                data: JSON.stringify(ids)
            
            });
        }
    })
    
});