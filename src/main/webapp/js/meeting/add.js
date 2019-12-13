var vm = new Vue({
    el: '#page-content',
    data: {
        dept:{

        }
    },
    methods: {
        selectAll: function () {
        axios({
            url:"/xbjy/dept/index"
        }).then(res =>{
            this.dept=res.data;
        }).catch(function (error) {
            console.log(error);
        });
        },
        selectUser:function () {

        }
    },
    created: function () {
        this.selectAll();
    }

});