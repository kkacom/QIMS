
    $(function(){
        fromAndroid();
    })
    //从from获取数据，转为对象
    function fromToJson(form) {
        //alert($.trim(" 1"));
        var result = {};
        var fieldArray = $('#' + form).serializeArray();

        for (var i = 0; i < fieldArray.length; i++) {
            var field = fieldArray[i];
            if (field.name in result) {
                result[field.name] += ',' + field.value;
            } else {
                result[field.name] = field.value;
            }
        }

        var json = JSON.stringify(result);
        // alert(json);
        return json;
    }

    function js2Android() {
//        alert("没有任何数据！");
        var json = fromToJson(document.forms[0].id);
        if (null == json||""==json || " " == json) {
            alert("没有任何数据！");
        }else{
            // alert(json);
            android.write2Database(json);
        }
        
    }

    function fromAndroid() {
          alert("调用了fromAndroid");
       /* var json = eval("(" + android.send2JS() + ")");
        alert(json);
        alert(json);
        for (var p in json) { //遍历json对象的每个key/value对,p为key
            // alert(p + " " + json[p]);
            $("input[name='" + p + "']").val(json[p]);
        }*/
    }
    
   // window.onload = fromAndroid;