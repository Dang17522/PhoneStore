app.controller("report-ctrl",function($scope,$http){
	$scope.items=[];
	$scope.cates = [];
	$scope.form={};

	$scope.initialize = function(){
		$http.get("/rest/reports").then(resp =>{
			$scope.items = resp.data;
			$scope.items.forEach(item =>{
				item.createDate = new Date(item.createDate)
			})
		});
		$http.get("/rest/categories").then(resp =>{
			$scope.cates = resp.data;
		});
		$http.get("/rest/brands").then(resp =>{
			$scope.brands = resp.data;
		});
	}
	$scope.initialize();

	$scope.reset = function(){
		$scope.form={
				createDate:new Date(),
				image:'logo.png',
				available:true,
				
		};
	}
	$scope.edit = function(item){
		$scope.form = angular.copy(item);
		$(".nav-tabs button:eq(0)").tab('show')
	}

	$scope.create = function(){
		var item = angular.copy($scope.form);
		$http.post(`/rest/products`,item).then(resp =>{
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Them moi thanh cong");
		}).catch(error =>{
			alert("Loi them moi san pham");
			console.log("Error",error);
		});
	}
	$scope.update = function(){
		var item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.id}`,item).then(resp=>{
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			alert("Cap Nhat Thanh Cong");
		}).catch(error=>{
			alert("Cap nhat that bai");
			console.log("Error",error);
		});
	}
	
	
	$scope.delete = function(item){
		$http.delete(`/rest/products/${item.id}`).then(resp=>{
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index,1);
			$scope.reset();
			alert("Xoa Thanh Cong");
		}).catch(error=>{
			alert("Xoa that bai");
			console.log("Error",error);
		});
	}
	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name;
		}).catch(error => {
			console.log(error);
			alert('Error upload image');
		})
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