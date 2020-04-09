var interval =0;
var contentBefore ="";
$(document).ready(function() {
    $("textarea").keyup(function() {
       contentChanged();
   });
   setTimer();
});
function setTimer()
{
if(document.getElementById("submitBTN").value=="Return to add note")
{
    contentBefore = document.getElementById("content").value;
    interval = setInterval("timer()",5000);
    
}
else
{
    clearInterval(interval);
}
}
function timer()
{
    
    if(document.getElementById("content").value != contentBefore)
    {
        contentChanged();
    }
    else
    {
        $.post("note?action=reset&id="+$("#selectedNote").val()+"&content=" + $("#content").val());
    }
}
function contentChanged()
{
    $.post("note?action=save&id="+$("#selectedNote").val()+"&content=" + $("#content").val(), function(response) {
           document.getElementById("message").innerHTML = response;
           contentBefore = document.getElementById("content").value;
           setTimeout("removeMessage()",2000);
           
       });
}

function removeMessage()
{
    document.getElementById("message").innerHTML = "";
}

