app.controller("authority-ctrl", function($scope, $http, $location) {
	$scope.roles = [];
	$scope.admins = [];
	$scope.authorities = [];
	var email ="";
	

	$scope.items = [];
	$scope.items1 = [];
	$scope.form = {};
	
	$scope.reset = function(){
		$scope.form={
				createDate:new Date(),
				photo:'user.png'
				
		};
	}
	$scope.initialize = function() {
		$http.get("/rest/roles").then(resp => {
			$scope.roles = resp.data;
		});
		$http.get("/rest/accounts/list").then(resp => {
			$scope.items = resp.data;

		});
		$scope.reset();
	}

	$http.get("/rest/accounts?admin=true").then(resp => {
		$scope.admins = resp.data;
	})

	$http.get("/rest/authorities?admin=true").then(resp => {
		$scope.authorities = resp.data;
	}).catch(error => {
		$location.path("/unauthorized");
	})
	$scope.authority_of = function(acc, role) {
		if ($scope.authorities) {
			return $scope.authorities.find(ur => ur.user.username == acc.username && ur.role.id == role.id);
		}
	}

	$scope.authority_changed = function(acc, role) {
		var authority = $scope.authority_of(acc, role);
		if (authority) {
			$scope.revoke_authority(authority);
		} else {
			authority = { user: acc, role: role };
			$scope.grant_authority(authority);
		}
	}
	$scope.grant_authority = function(authority) {
		$http.post(`/rest/authorities`, authority).then(resp => {
			$scope.authorities.push(resp.data)
			alert("Cấp quyền sử dụng thành công");
		}).catch(error => {
			alert("Cấp quyền sử dụng thất bại");
			console.log("Error", error);
		})
	}
	$scope.revoke_authority = function(authority) {
		$http.delete(`/rest/authorities/${authority.id}`).then(resp => {
			var index = $scope.authorities.findIndex(a => a.id == authority.id);
			$scope.authorities.splice(index, 1);
			alert("Thu hồi quyền thành công");
		}).catch(error => {
			alert("Thu hồi quyền thất bại");
			console.log("Error", error);
		});
	}
	$scope.initialize();

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

	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		var username = angular.copy(item.username);
		$(".nav-tabs button:eq(1)").tab('show')
	}

	$scope.create = function() {
		var item = angular.copy($scope.form);
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/; 
		email = $('#email').val();
		
		if($scope.form.username == null){
			alert("User không được bỏ trống");
		}
		else if($scope.form.fullname == null){
			alert("full name không được bỏ trống");
		}else if(!filter.test(email)){
			alert("Sai định dạng email");
		}else if($scope.form.password == null){
			alert("Mật khẩu không được bỏ trống");
		}else if($scope.form.address == null){
			alert("Địa chỉ không được bỏ trống");
		}else if($scope.form.phone == null || $scope.form.phone.length <10 || $scope.form.phone.length>11){
			alert("SDT không được bỏ trống và phải đủ 10 số");
		}
		else{
			
			if($scope.form.status == null){
				$scope.form.status = true;
			}
			$http.post(`/rest/accounts`, item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.items.push(resp.data);
			$scope.initialize();
			alert("Thêm Người dùng mới Thành công");
		}).catch(error => {
			alert("Lỗi thêm mới User");
			console.log("Error", error);
		});
		}
	}
	$scope.update = function() {
		var item = angular.copy($scope.form);
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/; 
		email = $('#email').val();
		
		if($scope.form.username == null){
			alert("Tên người dùng không được bỏ trống");
		}
		else if($scope.form.fullname == null){
			alert("Họ tên không được bỏ trống");
		}else if(!filter.test(email)){
			alert("Sai định dạng email");
		}else if($scope.form.password == ''){
			alert("Mật khẩu không được bỏ trống");
		}else if($scope.form.address == null){
			alert("Địa chỉ không được bỏ trống");
		}else if($scope.form.phone == null || $scope.form.phone.length <10 || $scope.form.phone.length>11){
			alert("Số điện thoại không được bỏ trống và phải đủ 10 số");
		}else{
			$http.put(`/rest/accounts/${item.username}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.username == item.username);
			$scope.items[index] = item;
			alert("Cập nhật thành công");
		}).catch(error => {
			alert("Cập nhật thất bại");
			console.log("Error", error);
		});
		}
		
	}


	$scope.delete = function(item) {
		$http.delete(`/rest/accounts/${item.username}`).then(resp => {
			var index = $scope.items.findIndex(p => p.username == item.username);
			$scope.items.splice(index, 1);
			$scope.initialize();
			alert("Xóa người dùng thành công");
		}).catch(error => {
			alert("Xóa người dùng thất bại");
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
			$scope.form.photo = resp.data.name;
		}).catch(error => {
			console.log(error);
			alert('Lỗi tải lên hình ảnh');
		})
	}
	

});