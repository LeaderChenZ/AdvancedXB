var vm = new Vue({
    el: '#main-container',
    data: {
        pageInfo: {
            pageNum: 1,
            pageSize: 5
        },
        params:{
            pageNum: '',
            pageSize: '',
            title:'',
            id:''
        }
    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            let userInfo =sessionStorage.getItem("userInfo");
            let user =  JSON.parse(userInfo);
            this.params.id = user.id;
            axios({
                url: "/xbjy/article/list",
                method: "post",
                data: this.params
            }).then(res => {
                this.pageInfo = res.data;
            }).catch(function (error) {
                console.log(error)
            });
        },
        toDetail: function (id) {
            let userInfo =sessionStorage.getItem("userInfo");
            let user =  JSON.parse(userInfo);
            let uid = user.id;
            axios({
                url: "/xbjy/article/selectByAId",
                params: {aid: id,uid:uid}
            }).then(res => {
                layer.obj = res.data;
                let index = layer.open({
                    type: 2,
                    title: "文章详细信息",
                    content: '/html/article_detail.html',
                    area: ['80%', '80%']
                })
            }).catch(function (error) {
                console.log(error)
            })
        },
        toUpdate: function () {

        location.href = "/html/article_add.html"
        }
    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    }

});