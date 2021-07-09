$("#treningSubmit").on("click", function(event){
    event.preventDefault();

    let id=window.localStorage.getItem("treningId");
    let naziv = $("#naziv").val();
    let opis = $("#opis").val();
    let tipTreninga = $("#tipTreninga").val();
    let trajanje = $("#trajanje").val();

    let updateTrening = {
        naziv,
        opis,
        tipTreninga,
        trajanje
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8090/api/treninzi/update/"+id,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(updateTrening),
        success: function(response){
            console.log(response);
            alert("Trening " +response.id + " je izmenjen");
            window.location.href="trenerTreninzi.html";
        },
        error:function(e){
            alert("Greska prilikom izmene");
        }


    });


});