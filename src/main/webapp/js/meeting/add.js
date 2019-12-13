var vm = new Vue({
    el: '#page-content',
    data: {
        dept:{

        },
        userName:{}
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
            let did  = $("#deptId").val();
            axios({
                url:"/xbjy/dept/selectUser",
                params:{
                    did:did
                }
            }).then(res =>{
                this.userName=res.data;
            }).catch(function (error) {
                console.log(error);
            });
        }
    },
    created: function () {
        $("#deptId").append('<option value="" selected>请选择部门</option>');
        this.selectAll();
    },
    updated: function () {
        $("#deptId").selectpicker('refresh');
        $("#userIds").selectpicker('refresh');
    }
})