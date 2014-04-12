$('form').validate({
    onKeyup : true,
    eachValidField : function() {

        $(this).closest('div').removeClass('error').addClass('success');
    },
    eachInvalidField : function() {

        $(this).closest('div').removeClass('success').addClass('error');
    }
});