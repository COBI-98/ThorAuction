// update.js

$("#update_btn").on("click", () => {
    this.update();
})

update:function(){
    let data = {
        id: $("#id").val(),
        password:$("password").val(),
        email:$("#email").val()
    };

    $.ajax({
        type:"PUT"
    })
}