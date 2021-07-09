 $("#treningSubmit").on("click", function(event){
        event.preventDefault();
        
        let id = window.localStorage.getItem("Id");
        let naziv = $("#naziv").val();
        let opis = $("#opis").val();
        let tipTreninga = $("#tipTreninga").val();
        let trajanje = $("#trajanje").val();
    
        let newTrening = {
            naziv,
            opis,
            tipTreninga,
            trajanje

        }
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/api/treninzi/dodaj/"+id,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(newTrening),
            success: function(response){
                console.log(response);
    
                alert("Trening" +response.id + "je dodat");
                window.location.href = "trenerTreninzi.html";
    
            },
            error:function(e){
                alert("Greska prilikom dodavanja");
            }
    
        });
    
    
    });
    
