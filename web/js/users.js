$(document).ready(function() {
    $("form").submit(function(event) {
        $.get($(this).attr("action"), $(this).serialize(), function(responseJson) {
            $("#username").val(responseJson.username);
            $("#firstname").val(responseJson.firstname);
            // more
            
        });
        event.preventDefault();  // stop the form submission
    });
});
