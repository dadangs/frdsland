<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside class="main-sidebar" ng-controller="sidebarController">
	<section class="sidebar">
		<ul class="sidebar-menu">
			<li class="header">MAIN NAVIGATION</li>

			<li class="treeview"><a href="#"> <i
					class="fa ${menu.iconMenu}"></i> <span>MASTER DATA</span> <span
					class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">
					<li><a href="<c:url value='/protected/master/users'/>"><i
							class="fa ${menu.iconMenu}"></i> <span>Data User Login</span></a></li>
					<li><a href="<c:url value='/protected/master/developers'/>"><i
							class="fa ${menu.iconMenu}"></i> <span>Data Developer</span></a></li>
					<li><a href="<c:url value='/protected/master/proyeks'/>"><i
							class="fa ${menu.iconMenu}"></i> <span>Data Proyek</span></a></li>
					<li><a href="<c:url value='/protected/master/rumahs'/>"><i
							class="fa ${menu.iconMenu}"></i> <span>Data Produk</span></a></li>
					<li><a href="<c:url value='/protected/master/konsumens'/>"><i
							class="fa ${menu.iconMenu}"></i> <span>Data Konsumen</span></a></li>
				</ul></li>

			<li><a href="<c:url value='/protected/proses/transaksis'/>"><i
					class="fa ${menu.iconMenu}"></i> <span>TRANSAKSI</span></a></li>
			<li class="treeview"><a href="#"> <i
					class="fa ${menu.iconMenu}"></i> <span>PEMBAYARAN</span> <span
					class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">
					<li><a href="<c:url value='/protected/proses/pembayarans'/>"><i
							class="fa ${menu.iconMenu}"></i> <span>Angsuran</span></a></li>
					<li><a
						href="<c:url value='/protected/proses/pembayarans_dp'/>"><i
							class="fa ${menu.iconMenu}"></i> <span>Down Payment</span></a></li>
				</ul></li>
			<li class="treeview"><a href="#"> <i
					class="fa ${menu.iconMenu}"></i> <span>LAPORAN</span> <span
					class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li><a href="<c:url value='/protected/laporan/rekap_piutangs'/>"><i
							class="fa ${menu.iconMenu}"></i> <span>Rekap Piutang</span></a></li>

					<!-- <li class="treeview"><a href="#"> <i
							class="fa ${menu.iconMenu}"></i> <span>Konsumen</span> <span
							class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a
								href="<c:url value='/protected/master/lap_konsumens'/>"><i
									class="fa ${menu.iconMenu}"></i> <span>Per Konsumen</span></a></li>
							<li><a
								href="<c:url value='/protected/master/lap_allkonsumens'/>"><i
									class="fa ${menu.iconMenu}"></i> <span>Keseluruhan</span></a></li>
						</ul></li> -->

					<li><a href="<c:url value='/protected/laporan/pencarians'/>"><i
							class="fa ${menu.iconMenu}"></i> <span>Pencarian</span></a></li>
				</ul></li>
		</ul>
	</section>
</aside>

<script src="<c:url value="/resources/lte/dist/js/sidebar.js" />"></script>