app.controller("multipleimage-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.form = {};

	$scope.reset = function() {
		$scope.form = {
			name: 'phonestore.png'
		};
	}

	$scope.initialize = function() {
		$http.get("/rest/multiimages").then(resp => {
			$scope.items = resp.data;
		});
		$http.get("/rest/products").then(resp => {
			$scope.products = resp.data;
		});
		$scope.reset();
	}
	$scope.initialize();


	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-tabs button:eq(0)").tab('show')
	}

	$scope.create = function() {
		var item = angular.copy($scope.form);
		if ($scope.form.product == null) {
			alert("Sản phẩm không được bỏ trống ");
		} else if ($('#image').val() == '') {
			alert("Hình ảnh không được bỏ trống ");
		} else {
			$http.post(`/rest/multiimages`, item).then(resp => {
				$scope.items.push(resp.data);
				$scope.reset();
				alert("Thêm mới thành công");
			}).catch(error => {
				alert("Lỗi thêm mới hình ảnh");
				console.log("Error", error);
			});
		}

	}
	$scope.update = function() {
		var item = angular.copy($scope.form);
		if ($scope.form.product == null) {
			alert("Sản phẩm không được bỏ trống");
		} else {
			$http.put(`/rest/multiimages/${item.id}`, item).then(resp => {
				var index = $scope.items.findIndex(p => p.id == item.id);
				$scope.items[index] = item;
				$scope.reset();
				alert("Cập nhật thành công");
			}).catch(error => {
				alert("Cập nhật thất bại");
				console.log("Error", error);
			});
		}

	}


	$scope.delete = function(item) {
		$http.delete(`/rest/multiimages/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			alert("Xoa thành công");
		}).catch(error => {
			alert("Xoa thất bại");
			console.log("Error", error);
		});
	}
	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.name = resp.data.name;
		}).catch(error => {
			console.log(error);
			alert('Lỗi tải lên hình ảnh');
		})
	}


	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}
});