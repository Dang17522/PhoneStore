app.controller("order-ctrl",function($scope,$http){
	$scope.items=[];
	$scope.orderdetail = [];
	$scope.form={};

	$scope.initialize = function(){
		$http.get("/rest/orders").then(resp =>{
			$scope.items = resp.data;
			
		});
		$http.get("/rest/ordertracks").then(resp => {
			$scope.ordertracks = resp.data;
		});
		
	}
	$scope.initialize();

	
	$scope.edit = function(item){
		$scope.form = angular.copy(item);
		$scope.form.ress = angular.copy(item.address) + " "+angular.copy(item.district) +" "+ angular.copy(item.province);
		
		
		$(".nav-tabs button:eq(0)").tab('show')
	}
	$scope.update = function(){
		var item = angular.copy($scope.form);
		if($scope.form.address == ''){
			alert("Address không được bỏ trống");
		}
		else if($scope.form.district == null){
			alert("Quận không được bỏ trống");
		}else if($scope.form.province == null){
			alert("Tỉnh không được bỏ trống");
		}else if($scope.form.phone == null || $scope.form.phone.length <10 || $scope.form.phone.length>11){
			alert("SDT không được bỏ trống và phải đủ 10 số");
		}else{
			$http.put(`/rest/orders/${item.id}`,item).then(resp=>{
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			alert("Cập nhật thành công");
			 location.reload();
		}).catch(error=>{
			alert("Cập nhật thất bại");
			console.log("Error",error);
		});
		}
		
	}
	
	
	
	
	
	
	
	
	$scope.pager = {
		page:0,
		size: 10,
		get items(){
			var start = this.page * this.size;
			return $scope.items.slice(start,start + this.size);
		},
		get count(){
			return Math.ceil(1.0 * $scope.items.length/ this.size);
		},
		first(){
			this.page = 0;
		},
		prev(){
			this.page--;
			if(this.page <0){
				this.last();
			}
		},
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			}
		},
		last(){
			this.page = this.count-1;
		}
	}
});