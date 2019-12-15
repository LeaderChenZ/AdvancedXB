
var vm = new Vue({
    el: '.myBox',
    data: function() {
        return{
        params:{
            username:'',
            password:'',
            password2:'',
            email:''
        }
        }
    },
    methods: {
        register:function () {
            console.log(this.params);
            axios({
                url:"/xbjy/login/register",
                data:this.params,
                method:"post"
            }).then(res =>{
                layer.msg(res.data.msg);
                if(res.data.success==false){//注册失败
                    layer.msg(data.msg,{time:500},function(){
                        location.href='register.html';
                    });
                    layer.msg(res.data.msg)
                }else {//注册成功
                    layer.msg(res.data.msg,{time:500},function(){
                        location.href='index.html';
                    });
                }
            }).catch(function (error) {
                console.log(error)
            })
        }
    },
    created: function () {

    }

});