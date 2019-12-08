var vm = new Vue({
    el: '#main-container',
    data: {
        pageInfo: {
            pageNum: 1,
            pageSize: 3
        },
        params:{
            title:'',
            status:''
        }

    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            console.log(this.params.status)
            axios({
                url: "/xbjy/meeting/index",
                method: "post",
                data: this.params
            }).then(res => {
                this.pageInfo = res.data;
            }).catch(function (error) {
                console.log(error)
            });
        }

    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    }

});