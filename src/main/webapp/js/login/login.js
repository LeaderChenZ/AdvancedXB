
var vm = new Vue({
    el: '.myBox',
    data: function() {
        return{
        params:{
            username:'',
            password:'',
            code:''
        }
        }
    },
    methods: {
        login:function () {
            axios({
                url:"/xbjy/login",
                data:this.params,
                method:"post"
            }).then(res =>{
                if(res.data.success){
                    //成功则将返回信息放入前端的状态管理中，跳转到home.html
                    //将后台返回的key:value结构数据 转换成json串  放入到userInfo里面
                    //再将userInfo放入到前端的会话对象sessionStorage中，该sessionStorage对象是整个浏览器不关闭的时候都存在的
                    sessionStorage.setItem("userInfo",JSON.stringify(res.data.obj));
                    location.href="/html/home.html";
                }else{
                    layer.msg(res.data.msg);
                }
            }).catch(function (error) {
                console.log(error)
            })
        }
    },
    created: function () {

    }

});