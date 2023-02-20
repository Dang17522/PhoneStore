app.controller("voucher-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.products = [];
	$scope.users = [];
	$scope.form = {};

	$scope.initialize = function() {
		$http.get("/rest/vouchers").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate)
			})
		});
		$http.get("/rest/products").then(resp => {
			$scope.products = resp.data;
		});
		$http.get("/rest/accounts").then(resp => {
			$scope.users = resp.data;
		});
	}
	$scope.initialize();

	$scope.reset = function() {
		$scope.form = {


		};
	}
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-tabs button:eq(0)").tab('show')
	}
	$scope.random = function() {
		let r = Math.random().toString(36).substring(7);
		$scope.form.voucherCode = r;

	}
	$scope.create = function() {
		var item = angular.copy($scope.form);
		if ($scope.form.voucherCode == null) {
			alert("Mã giảm giá không được bỏ trống");
		}
		else if ($scope.form.maxUser == null) {
			alert("Người dùng tối đa không được bỏ trống");
		} else if ($scope.form.discountAmount == null) {
			alert("Số tiền giảm giá không được bỏ trống");
		} else if ($scope.form.status == null) {
			alert("Vui lòng chọn Trạng thái");
		} else if ($scope.form.startDate == null) {
			alert("Vui lòng chọn ngày bắt đầu giảm giá");
		} else if ($scope.form.endDate == null) {
			alert("Vui lòng chọn ngày kết thúc giảm giá");
		} else if ($scope.form.product == null) {
			alert("Vui lòng chọn sản phẩm");
		} else {
			$http.post(`/rest/vouchers`, item).then(resp => {
				$scope.items.push(resp.data);
				location.reload();
				$scope.reset();
				alert("Thêm mới mã giảm giá thành công");
			}).catch(error => {
				alert("Lỗi thêm mới mã giảm giá");
				console.log("Error", error);
			});
		}

	}
	$scope.update = function() {
		var item = angular.copy($scope.form);
		if ($scope.form.voucherCode == '') {
			alert("Mã giảm giá không được bỏ trống");
		}
		else if ($scope.form.maxUser == null) {
			alert("Người dùng tối đa không được bỏ trống");
		} else if ($scope.form.discountAmount == null) {
			alert("Số tiền giảm giá không được bỏ trống");
		} else {
			$http.put(`/rest/vouchers/${item.id}`, item).then(resp => {
				var index = $scope.items.findIndex(p => p.id == item.id);
				$scope.items[index] = item;
				location.reload();
				$scope.reset();
				alert("Cập nhật thành công");
			}).catch(error => {
				alert("Cập nhật thất bại");
				console.log("Error", error);
			});
		}

	}


	$scope.delete = function(item) {
		$http.delete(`/rest/vouchers/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			alert("Xóa thành công");
		}).catch(error => {
			alert("Xóa thất bại");
			console.log("Error", error);
		});
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