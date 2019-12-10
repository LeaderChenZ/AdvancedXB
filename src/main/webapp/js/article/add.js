var vm = new Vue({
    el: '#main-container',
    data: {
        article:{
            title:'',
            content:''
        }
    },
    methods: {
        save: function () {
            axios({
                url: "/xbjy/article/insertArticle",
                data:this.article,
                method:"post"
            }).then(res => {
                layer.msg(res.data.msg);
                location.href = "/html/article.html"
            }).catch(function (error) {
                console.log(error)
            })
        },
    },
    created: function () {

    }

});