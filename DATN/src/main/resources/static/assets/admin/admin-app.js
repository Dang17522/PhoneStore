app = angular.module("admin-app",["ngRoute"]);

app.config(function($routeProvider) {
	$routeProvider
	.when("/product",{
		templateUrl:"/assets/admin/product/index.html",
		controller:"product-ctrl"
	})
	.when("/authorize",{
		templateUrl:"/assets/admin/authority/layout.html",
		controller:"authority-ctrl"
	})
	.when("/unauthorized",{
		templateUrl:"/assets/admin/authority/unauthorized.html",
		controller:"authority-ctrl"
	})
	.when("/voucher",{
		templateUrl:"/assets/admin/voucher/index.html",
		controller:"voucher-ctrl"
	})
	.when("/order",{
		templateUrl:"/assets/admin/order/index.html",
		controller:"order-ctrl"
	})
	.when("/image",{
		templateUrl:"/assets/admin/MultipleImage/index.html",
		controller:"multipleimage-ctrl"
	})
	.when("/line",{
		templateUrl:"/report/today",
		
	})
	.when("/linemonth",{
		templateUrl:"/report/month",
		
	})
	.when("/lineyear",{
		templateUrl:"/report/year",
		
	})
	.when("/customer",{
		templateUrl:"/report/user",
		
	})
	.when("/send",{
		templateUrl:"/sendvoucher",
		
	})
	.otherwise({
		templateUrl:"/assets/admin/video.html"
	});
})