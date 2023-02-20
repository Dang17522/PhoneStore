app.controller("product-ctrl",function($scope,$http){
	$scope.items=[];
	$scope.cates = [];
	$scope.discounts = [];
	$scope.form={};
	
	$scope.reset = function(){
		$scope.form={
				createDate:new Date(),
				image:'phonestore.png',
				available:true,
				
		};
	}
	
	$scope.initialize = function(){
		$http.get("/rest/products").then(resp =>{
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
		$http.get("/rest/discounts").then(resp =>{
			$scope.discounts = resp.data;
		});
		$scope.reset();
	}
	$scope.initialize();

	
	$scope.edit = function(item){
		$scope.form = angular.copy(item);
		$(".nav-tabs button:eq(0)").tab('show')
	}

	$scope.create = function(){
		var item = angular.copy($scope.form);
		if($scope.form.name == null){
			alert("Tên sản phẩm không được bỏ trống");
		}
		else if($scope.form.price == null){
			alert("Giá không được bỏ trống");
		}else if ($('#image').val() == '') {
			alert("Hình ảnh không được bỏ trống ");
		}else if($scope.form.category==null){
			alert("Loại sản phẩm không được bỏ trống");
		}else if($scope.form.brand == null){
			alert("Hãng không được bỏ trống");
		}else if($scope.form.color == null ){
			alert("Màu không được bỏ trống ");
		}else if($scope.form.quantity == null ){
			alert("Số lượng không được bỏ trống ");
		}else{
			$http.post(`/rest/products`,item).then(resp =>{
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Thêm mới thành công");
		}).catch(error =>{
			alert("Lỗi thêm mới sản phẩm");
			console.log("Error",error);
		});
		}
		
	}
	$scope.update = function(){
		var item = angular.copy($scope.form);
		if($scope.form.name == ''){
			alert("Tên sản phẩm không được bỏ trống");
		}
		else if($scope.form.price == null){
			alert("Giá không được bỏ trống");
		}else if($scope.form.category==null){
			alert("Loại sản phẩm không được bỏ trống");
		}else if($scope.form.brand == null){
			alert("Hãng không được bỏ trống");
		}else if($scope.form.color == '' ){
			alert("Màu không được bỏ trống ");
		}else if($scope.form.quantity == null ){
			alert("Số lượng không được bỏ trống ");
		}else{
			$http.put(`/rest/products/${item.id}`,item).then(resp=>{
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			alert("Cập nhật thành công");
		}).catch(error=>{
			alert("Cập nhật thất bại");
			console.log("Error",error);
		});
		}
		
	}
	
	
	$scope.delete = function(item){
		$http.delete(`/rest/products/${item.id}`).then(resp=>{
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index,1);
			$scope.reset();
			alert("Xóa thành công");
		}).catch(error=>{
			alert("Xóa thất bại");
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
			alert('Lỗi tải lên hình ảnh');
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