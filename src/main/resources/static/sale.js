$(document).ready(function() {

    let id=window.localStorage.getItem("fitnesCentarSalaId");
    console.log(id);

    $.ajax({
        type: "GET",
        url: "http://localhost:8090/api/sale/fitnesCentarSale/"+id,
        dataType: "json",
        success: function(response) {
            console.log("USPESNO", response);
            console.log(response);

            for(let sala of response){
                let row = "<tr>";
                row+= "<td>" + sala.id + "</td>";
                row+= "<td>" + sala.kapacitet + "</td>";
                row+= "<td>" + sala.oznakaSale + "</td>";
                row+="<td><button class='izmeni' onClick=window.location.href='editSala.html' data-id='"+sala.id+"'  >EDIT</button></td>";
                row+= "<td><input type='checkbox' data='"+sala.id+"'></td>"
                row+="</tr>";

                $('.content-table').append(row);
            }
        },
        error: function(response) {
            console.log("ERROR: ", response);
        }

    });

    $(document).on('click', '.izmeni', function(){
        let salaId = this.dataset.id;
        console.log(salaId);
        localStorage.setItem("salaId", salaId);
    })


    $("#potvrdi").on("click", function(event){
        event.preventDefault();
        let ids=[]
        $("[type='checkbox']").each(function(elem){
            console.log(this)
            if($(this).is(":checked")){
                ids.push($(this).attr("data"));
                window.location.href="sale.html"
            }
        })
        let duzina=ids.length;
        for(var i=0; i<duzina; i++)
        {
            $.ajax({
                type:"DELETE",
                url:"http://localhost:8090/api/sale/delete/"+ids[i],
                sataType:"json",
                contentType: "application/json",
                data: JSON.stringify(ids)
            
            });
        }
    })
 
});