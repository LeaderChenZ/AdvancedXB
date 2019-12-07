var vm = new Vue({
    el: '#main-container',
    data: {
        pageInfo:{
            pageNum:1,
            pageSize:3
        },
        focus:{
            pageNum:'',
            pageSize:''
        }
    },
    methods: {

        selectAll: function (pageNum,pageSize) {
            this.focus.pageNum = pageNum;
            this.focus.pageSize = pageSize;
            axios({
                url: "/xbjy/user/selectFocus",
                method: "post",
                data: this.focus
            }).then(res => {
                this.pageInfo = res.data;
            }).catch(function (error) {
                console.log(error)
            });

        },

        deleteById: function () {

        },

    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
    }

});