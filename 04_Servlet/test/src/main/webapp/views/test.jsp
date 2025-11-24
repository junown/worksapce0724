<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>크리에이터 ERP 시스템</title>
    
    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>
    
    <!-- Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>
    
    <!-- Lucide Icons -->
    <script src="https://unpkg.com/lucide@latest"></script>
    
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
            background-color: #f8fafc;
        }

        .sidebar {
            width: 250px;
            background: white;
            border-right: 1px solid #e2e8f0;
            height: 100vh;
            position: fixed;
            left: 0;
            top: 0;
            overflow-y: auto;
            transition: transform 0.3s ease;
        }

        .sidebar.hidden {
            transform: translateX(-100%);
        }

        .sidebar-header {
            padding: 1.5rem;
            border-bottom: 1px solid #e2e8f0;
        }

        .sidebar-header h1 {
            font-size: 1.25rem;
            font-weight: 700;
            color: #7c3aed;
        }

        .sidebar-menu {
            padding: 1rem;
        }

        .menu-label {
            font-size: 0.75rem;
            font-weight: 600;
            color: #64748b;
            padding: 0.5rem 0.75rem;
            text-transform: uppercase;
            letter-spacing: 0.05em;
        }

        .menu-item {
            display: flex;
            align-items: center;
            gap: 0.75rem;
            padding: 0.75rem;
            margin-bottom: 0.25rem;
            border-radius: 0.375rem;
            cursor: pointer;
            color: #334155;
            transition: all 0.2s;
        }

        .menu-item:hover {
            background-color: #f1f5f9;
        }

        .menu-item.active {
            background-color: #ede9fe;
            color: #7c3aed;
        }

        .main-content {
            margin-left: 250px;
            min-height: 100vh;
            transition: margin-left 0.3s ease;
        }

        .main-content.full-width {
            margin-left: 0;
        }

        .top-bar {
            background: white;
            border-bottom: 1px solid #e2e8f0;
            padding: 1rem 1.5rem;
            display: flex;
            align-items: center;
            gap: 1rem;
            position: sticky;
            top: 0;
            z-index: 10;
        }

        .menu-toggle {
            cursor: pointer;
            padding: 0.5rem;
            border-radius: 0.375rem;
        }

        .menu-toggle:hover {
            background-color: #f1f5f9;
        }

        .content-area {
            padding: 1.5rem;
        }

        .card {
            background: white;
            border-radius: 0.5rem;
            border: 1px solid #e2e8f0;
            overflow: hidden;
        }

        .card-header {
            padding: 1.5rem;
            border-bottom: 1px solid #e2e8f0;
        }

        .card-title {
            font-size: 1.125rem;
            font-weight: 600;
            color: #0f172a;
        }

        .card-description {
            font-size: 0.875rem;
            color: #64748b;
            margin-top: 0.25rem;
        }

        .card-content {
            padding: 1.5rem;
        }

        .stat-card {
            background: white;
            border-radius: 0.5rem;
            border: 1px solid #e2e8f0;
            padding: 1.5rem;
        }

        .stat-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 1rem;
        }

        .stat-title {
            font-size: 0.875rem;
            font-weight: 500;
            color: #64748b;
        }

        .stat-value {
            font-size: 1.875rem;
            font-weight: 700;
            color: #0f172a;
        }

        .stat-change {
            font-size: 0.75rem;
            color: #64748b;
            margin-top: 0.25rem;
        }

        .stat-change .positive {
            color: #16a34a;
        }

        .badge {
            display: inline-flex;
            align-items: center;
            padding: 0.25rem 0.625rem;
            font-size: 0.75rem;
            font-weight: 500;
            border-radius: 0.375rem;
            white-space: nowrap;
        }

        .badge-default {
            background-color: #ede9fe;
            color: #7c3aed;
        }

        .badge-secondary {
            background-color: #f1f5f9;
            color: #475569;
        }

        .badge-outline {
            background-color: transparent;
            border: 1px solid #cbd5e1;
            color: #475569;
        }

        .progress-bar {
            width: 100%;
            height: 0.5rem;
            background-color: #f1f5f9;
            border-radius: 9999px;
            overflow: hidden;
        }

        .progress-fill {
            height: 100%;
            background-color: #7c3aed;
            transition: width 0.3s ease;
        }

        .btn {
            display: inline-flex;
            align-items: center;
            gap: 0.5rem;
            padding: 0.5rem 1rem;
            font-size: 0.875rem;
            font-weight: 500;
            border-radius: 0.375rem;
            cursor: pointer;
            transition: all 0.2s;
            border: none;
        }

        .btn-primary {
            background-color: #7c3aed;
            color: white;
        }

        .btn-primary:hover {
            background-color: #6d28d9;
        }

        .btn-outline {
            background-color: transparent;
            border: 1px solid #cbd5e1;
            color: #475569;
        }

        .btn-outline:hover {
            background-color: #f1f5f9;
        }

        .tab-list {
            display: flex;
            gap: 0.25rem;
            border-bottom: 1px solid #e2e8f0;
            margin-bottom: 1.5rem;
        }

        .tab {
            padding: 0.75rem 1rem;
            font-size: 0.875rem;
            font-weight: 500;
            color: #64748b;
            cursor: pointer;
            border-bottom: 2px solid transparent;
            transition: all 0.2s;
        }

        .tab:hover {
            color: #334155;
        }

        .tab.active {
            color: #7c3aed;
            border-bottom-color: #7c3aed;
        }

        .tab-content {
            display: none;
        }

        .tab-content.active {
            display: block;
        }

        .table {
            width: 100%;
            border-collapse: collapse;
        }

        .table thead {
            border-bottom: 1px solid #e2e8f0;
        }

        .table th {
            padding: 0.75rem;
            text-align: left;
            font-size: 0.875rem;
            font-weight: 500;
            color: #64748b;
        }

        .table td {
            padding: 0.75rem;
            border-bottom: 1px solid #f1f5f9;
            font-size: 0.875rem;
        }

        .grid {
            display: grid;
            gap: 1rem;
        }

        .grid-cols-1 { grid-template-columns: repeat(1, minmax(0, 1fr)); }
        .grid-cols-2 { grid-template-columns: repeat(2, minmax(0, 1fr)); }
        .grid-cols-3 { grid-template-columns: repeat(3, minmax(0, 1fr)); }
        .grid-cols-4 { grid-template-columns: repeat(4, minmax(0, 1fr)); }

        @media (min-width: 768px) {
            .md\\:grid-cols-2 { grid-template-columns: repeat(2, minmax(0, 1fr)); }
            .md\\:grid-cols-3 { grid-template-columns: repeat(3, minmax(0, 1fr)); }
        }

        @media (min-width: 1024px) {
            .lg\\:grid-cols-4 { grid-template-columns: repeat(4, minmax(0, 1fr)); }
            .lg\\:grid-cols-7 { grid-template-columns: repeat(7, minmax(0, 1fr)); }
        }

        .lg-col-span-3 { grid-column: span 3 / span 3; }
        .lg-col-span-4 { grid-column: span 4 / span 4; }

        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 50;
            align-items: center;
            justify-content: center;
        }

        .modal.show {
            display: flex;
        }

        .modal-content {
            background: white;
            border-radius: 0.5rem;
            padding: 1.5rem;
            max-width: 500px;
            width: 90%;
            max-height: 90vh;
            overflow-y: auto;
        }

        .modal-header {
            margin-bottom: 1rem;
        }

        .modal-title {
            font-size: 1.25rem;
            font-weight: 600;
            color: #0f172a;
        }

        .modal-description {
            font-size: 0.875rem;
            color: #64748b;
            margin-top: 0.25rem;
        }

        .form-group {
            margin-bottom: 1rem;
        }

        .form-label {
            display: block;
            font-size: 0.875rem;
            font-weight: 500;
            color: #334155;
            margin-bottom: 0.5rem;
        }

        .form-input,
        .form-select,
        .form-textarea {
            width: 100%;
            padding: 0.5rem 0.75rem;
            font-size: 0.875rem;
            border: 1px solid #cbd5e1;
            border-radius: 0.375rem;
            transition: all 0.2s;
        }

        .form-input:focus,
        .form-select:focus,
        .form-textarea:focus {
            outline: none;
            border-color: #7c3aed;
            box-shadow: 0 0 0 3px rgba(124, 58, 237, 0.1);
        }

        .form-textarea {
            resize: vertical;
            min-height: 80px;
        }

        .hidden {
            display: none;
        }

        @media (max-width: 768px) {
            .sidebar {
                transform: translateX(-100%);
            }

            .sidebar.show {
                transform: translateX(0);
            }

            .main-content {
                margin-left: 0;
            }
        }
    </style>
</head>
<body>
    <!-- Sidebar -->
    <aside class="sidebar" id="sidebar">
        <div class="sidebar-header">
            <h1>크리에이터 ERP</h1>
        </div>
        <nav class="sidebar-menu">
            <div class="menu-label">메뉴</div>
            <div class="menu-item active" data-page="dashboard">
                <i data-lucide="layout-dashboard" width="18" height="18"></i>
                <span>대시보드</span>
            </div>
            <div class="menu-item" data-page="schedule">
                <i data-lucide="calendar" width="18" height="18"></i>
                <span>콘텐츠 일정</span>
            </div>
            <div class="menu-item" data-page="revenue">
                <i data-lucide="dollar-sign" width="18" height="18"></i>
                <span>수익 관리</span>
            </div>
            <div class="menu-item" data-page="sponsorships">
                <i data-lucide="handshake" width="18" height="18"></i>
                <span>협찬 계약</span>
            </div>
        </nav>
    </aside>

    <!-- Main Content -->
    <main class="main-content" id="mainContent">
        <!-- Top Bar -->
        <div class="top-bar">
            <div class="menu-toggle" id="menuToggle">
                <i data-lucide="menu" width="24" height="24"></i>
            </div>
            <h2 id="pageTitle">대시보드</h2>
        </div>

        <!-- Content Area -->
        <div class="content-area">
            <!-- Dashboard Page -->
            <div id="page-dashboard" class="page-content">
                <!-- Stats Cards -->
                <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4" style="margin-bottom: 1.5rem;">
                    <div class="stat-card">
                        <div class="stat-header">
                            <span class="stat-title">이번 달 총 수익</span>
                            <i data-lucide="dollar-sign" width="16" height="16" style="color: #94a3b8;"></i>
                        </div>
                        <div class="stat-value">6,500만원</div>
                        <div class="stat-change">
                            <span class="positive">+24.5%</span> 전월 대비
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-header">
                            <span class="stat-title">주간 조회수</span>
                            <i data-lucide="eye" width="16" height="16" style="color: #94a3b8;"></i>
                        </div>
                        <div class="stat-value">159K</div>
                        <div class="stat-change">
                            <span class="positive">+18.2%</span> 지난주 대비
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-header">
                            <span class="stat-title">평균 참여율</span>
                            <i data-lucide="heart" width="16" height="16" style="color: #94a3b8;"></i>
                        </div>
                        <div class="stat-value">7.5%</div>
                        <div class="stat-change">
                            <span class="positive">+2.1%</span> 지난주 대비
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-header">
                            <span class="stat-title">진행중인 협찬</span>
                            <i data-lucide="trending-up" width="16" height="16" style="color: #94a3b8;"></i>
                        </div>
                        <div class="stat-value">3건</div>
                        <div class="stat-change">
                            총 계약금액 6,500만원
                        </div>
                    </div>
                </div>

                <!-- Charts -->
                <div class="grid grid-cols-1 lg:grid-cols-7" style="margin-bottom: 1.5rem; gap: 1rem;">
                    <div class="card lg-col-span-4">
                        <div class="card-header">
                            <div class="card-title">수익 추이</div>
                            <div class="card-description">최근 5개월 수익원별 변화</div>
                        </div>
                        <div class="card-content">
                            <canvas id="revenueChart" height="300"></canvas>
                        </div>
                    </div>
                    <div class="card lg-col-span-3">
                        <div class="card-header">
                            <div class="card-title">플랫폼별 수익 비중</div>
                            <div class="card-description">전체 수익 중 플랫폼별 기여도</div>
                        </div>
                        <div class="card-content">
                            <canvas id="platformChart" height="300"></canvas>
                        </div>
                    </div>
                </div>

                <!-- Engagement Chart -->
                <div class="card" style="margin-bottom: 1.5rem;">
                    <div class="card-header">
                        <div class="card-title">콘텐츠 성과 추이</div>
                        <div class="card-description">최근 7일간 조회수 및 참여율</div>
                    </div>
                    <div class="card-content">
                        <canvas id="engagementChart" height="100"></canvas>
                    </div>
                </div>

                <!-- Upcoming Content & Contracts -->
                <div class="grid grid-cols-1 lg:grid-cols-2" style="gap: 1rem;">
                    <div class="card">
                        <div class="card-header">
                            <div class="card-title">예정된 콘텐츠</div>
                            <div class="card-description">다가오는 업로드 일정</div>
                        </div>
                        <div class="card-content">
                            <div style="display: flex; flex-direction: column; gap: 1rem;">
                                <div style="display: flex; justify-content: space-between; align-items: center; padding-bottom: 1rem; border-bottom: 1px solid #f1f5f9;">
                                    <div style="display: flex; align-items: center; gap: 0.75rem;">
                                        <i data-lucide="youtube" width="20" height="20" style="color: #dc2626;"></i>
                                        <div>
                                            <div style="font-size: 0.875rem; font-weight: 500;">신제품 리뷰 영상</div>
                                            <div style="font-size: 0.75rem; color: #64748b; display: flex; align-items: center; gap: 0.25rem;">
                                                <i data-lucide="calendar" width="12" height="12"></i>
                                                2025-10-28
                                            </div>
                                        </div>
                                    </div>
                                    <span class="badge badge-default">편집중</span>
                                </div>
                                <div style="display: flex; justify-content: space-between; align-items: center; padding-bottom: 1rem; border-bottom: 1px solid #f1f5f9;">
                                    <div style="display: flex; align-items: center; gap: 0.75rem;">
                                        <i data-lucide="instagram" width="20" height="20" style="color: #e91e63;"></i>
                                        <div>
                                            <div style="font-size: 0.875rem; font-weight: 500;">일상 브이로그</div>
                                            <div style="font-size: 0.75rem; color: #64748b; display: flex; align-items: center; gap: 0.25rem;">
                                                <i data-lucide="calendar" width="12" height="12"></i>
                                                2025-10-29
                                            </div>
                                        </div>
                                    </div>
                                    <span class="badge badge-secondary">촬영완료</span>
                                </div>
                                <div style="display: flex; justify-content: space-between; align-items: center;">
                                    <div style="display: flex; align-items: center; gap: 0.75rem;">
                                        <div style="width: 20px; height: 20px; background: black; border-radius: 0.25rem;"></div>
                                        <div>
                                            <div style="font-size: 0.875rem; font-weight: 500;">챌린지 영상</div>
                                            <div style="font-size: 0.75rem; color: #64748b; display: flex; align-items: center; gap: 0.25rem;">
                                                <i data-lucide="calendar" width="12" height="12"></i>
                                                2025-10-30
                                            </div>
                                        </div>
                                    </div>
                                    <span class="badge badge-secondary">기획</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="card">
                        <div class="card-header">
                            <div class="card-title">활성 협찬 계약</div>
                            <div class="card-description">진행중인 계약 현황</div>
                        </div>
                        <div class="card-content">
                            <div style="display: flex; flex-direction: column; gap: 1rem;">
                                <div style="padding-bottom: 1rem; border-bottom: 1px solid #f1f5f9;">
                                    <div style="display: flex; justify-content: space-between; margin-bottom: 0.5rem;">
                                        <div>
                                            <div style="font-size: 0.875rem; font-weight: 500;">브랜드 A</div>
                                            <div style="font-size: 0.75rem; color: #64748b;">2,000,000원</div>
                                        </div>
                                        <span class="badge badge-default">진행중</span>
                                    </div>
                                    <div style="margin-top: 0.5rem;">
                                        <div style="display: flex; justify-content: space-between; font-size: 0.75rem; color: #64748b; margin-bottom: 0.25rem;">
                                            <span>진행률</span>
                                            <span>60%</span>
                                        </div>
                                        <div class="progress-bar">
                                            <div class="progress-fill" style="width: 60%;"></div>
                                        </div>
                                    </div>
                                </div>
                                <div style="padding-bottom: 1rem; border-bottom: 1px solid #f1f5f9;">
                                    <div style="display: flex; justify-content: space-between; margin-bottom: 0.5rem;">
                                        <div>
                                            <div style="font-size: 0.875rem; font-weight: 500;">브랜드 B</div>
                                            <div style="font-size: 0.75rem; color: #64748b;">1,500,000원</div>
                                        </div>
                                        <span class="badge badge-outline">대기중</span>
                                    </div>
                                    <div style="margin-top: 0.5rem;">
                                        <div style="display: flex; justify-content: space-between; font-size: 0.75rem; color: #64748b; margin-bottom: 0.25rem;">
                                            <span>진행률</span>
                                            <span>30%</span>
                                        </div>
                                        <div class="progress-bar">
                                            <div class="progress-fill" style="width: 30%;"></div>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <div style="display: flex; justify-content: space-between; margin-bottom: 0.5rem;">
                                        <div>
                                            <div style="font-size: 0.875rem; font-weight: 500;">브랜드 C</div>
                                            <div style="font-size: 0.75rem; color: #64748b;">3,000,000원</div>
                                        </div>
                                        <span class="badge badge-secondary">정산대기</span>
                                    </div>
                                    <div style="margin-top: 0.5rem;">
                                        <div style="display: flex; justify-content: space-between; font-size: 0.75rem; color: #64748b; margin-bottom: 0.25rem;">
                                            <span>진행률</span>
                                            <span>90%</span>
                                        </div>
                                        <div class="progress-bar">
                                            <div class="progress-fill" style="width: 90%;"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Schedule Page -->
            <div id="page-schedule" class="page-content hidden">
                <div style="display: flex; justify-content: space-between; align-items: start; margin-bottom: 1.5rem;">
                    <div>
                        <h3 style="font-size: 1.25rem; font-weight: 600; margin-bottom: 0.25rem;">콘텐츠 제작 일정</h3>
                        <p style="color: #64748b; font-size: 0.875rem;">플랫폼별 콘텐츠 일정을 관리하고 진행 상황을 추적하세요</p>
                    </div>
                    <button class="btn btn-primary" onclick="openScheduleModal()">
                        <i data-lucide="plus" width="16" height="16"></i>
                        새 일정 추가
                    </button>
                </div>

                <!-- Platform Stats -->
                <div class="grid grid-cols-1 md:grid-cols-3" style="margin-bottom: 1.5rem;">
                    <div class="stat-card">
                        <div class="stat-header">
                            <span class="stat-title">YouTube</span>
                            <i data-lucide="youtube" width="16" height="16" style="color: #dc2626;"></i>
                        </div>
                        <div class="stat-value">2건</div>
                        <div class="stat-change">이번 주 예정</div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-header">
                            <span class="stat-title">Instagram</span>
                            <i data-lucide="instagram" width="16" height="16" style="color: #e91e63;"></i>
                        </div>
                        <div class="stat-value">2건</div>
                        <div class="stat-change">이번 주 예정</div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-header">
                            <span class="stat-title">TikTok</span>
                            <div style="width: 16px; height: 16px; background: black; border-radius: 0.25rem;"></div>
                        </div>
                        <div class="stat-value">2건</div>
                        <div class="stat-change">이번 주 예정</div>
                    </div>
                </div>

                <!-- Schedule Table -->
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">예정된 콘텐츠</div>
                        <div class="card-description">다가오는 제작 및 업로드 일정</div>
                    </div>
                    <div class="card-content">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>플랫폼</th>
                                    <th>제목</th>
                                    <th>일시</th>
                                    <th>상태</th>
                                    <th>썸네일</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <div style="display: flex; align-items: center; gap: 0.5rem;">
                                            <i data-lucide="youtube" width="18" height="18" style="color: #dc2626;"></i>
                                            <span>YouTube</span>
                                        </div>
                                    </td>
                                    <td>신제품 리뷰 - 갤럭시 S25</td>
                                    <td>
                                        <div>2025-10-28</div>
                                        <div style="color: #64748b; font-size: 0.75rem;">18:00</div>
                                    </td>
                                    <td><span class="badge badge-default">편집중</span></td>
                                    <td><span style="color: #64748b;">준비완료</span></td>
                                </tr>
                                <tr>
                                    <td>
                                        <div style="display: flex; align-items: center; gap: 0.5rem;">
                                            <i data-lucide="instagram" width="18" height="18" style="color: #e91e63;"></i>
                                            <span>Instagram</span>
                                        </div>
                                    </td>
                                    <td>일상 브이로그</td>
                                    <td>
                                        <div>2025-10-29</div>
                                        <div style="color: #64748b; font-size: 0.75rem;">12:00</div>
                                    </td>
                                    <td><span class="badge badge-default">촬영완료</span></td>
                                    <td><span style="color: #64748b;">작업중</span></td>
                                </tr>
                                <tr>
                                    <td>
                                        <div style="display: flex; align-items: center; gap: 0.5rem;">
                                            <div style="width: 18px; height: 18px; background: black; border-radius: 0.25rem;"></div>
                                            <span>TikTok</span>
                                        </div>
                                    </td>
                                    <td>댄스 챌린지</td>
                                    <td>
                                        <div>2025-10-30</div>
                                        <div style="color: #64748b; font-size: 0.75rem;">20:00</div>
                                    </td>
                                    <td><span class="badge badge-secondary">기획</span></td>
                                    <td><span style="color: #64748b;">대기</span></td>
                                </tr>
                                <tr>
                                    <td>
                                        <div style="display: flex; align-items: center; gap: 0.5rem;">
                                            <i data-lucide="youtube" width="18" height="18" style="color: #dc2626;"></i>
                                            <span>YouTube</span>
                                        </div>
                                    </td>
                                    <td>Q&A 영상</td>
                                    <td>
                                        <div>2025-11-01</div>
                                        <div style="color: #64748b; font-size: 0.75rem;">18:00</div>
                                    </td>
                                    <td><span class="badge badge-secondary">업로드완료</span></td>
                                    <td><span style="color: #64748b;">준비완료</span></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!-- Revenue Page -->
            <div id="page-revenue" class="page-content hidden">
                <!-- Revenue Stats -->
                <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4" style="margin-bottom: 1.5rem;">
                    <div class="stat-card">
                        <div class="stat-header">
                            <span class="stat-title">총 수익</span>
                            <i data-lucide="dollar-sign" width="16" height="16" style="color: #94a3b8;"></i>
                        </div>
                        <div class="stat-value">6,500만원</div>
                        <div class="stat-change">
                            <span class="positive">+27.3%</span> 전월 대비
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-header">
                            <span class="stat-title">광고 수익</span>
                            <i data-lucide="trending-up" width="16" height="16" style="color: #94a3b8;"></i>
                        </div>
                        <div class="stat-value">2,800만원</div>
                        <div class="stat-change">전체의 43%</div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-header">
                            <span class="stat-title">협찬 수익</span>
                            <i data-lucide="wallet" width="16" height="16" style="color: #94a3b8;"></i>
                        </div>
                        <div class="stat-value">2,000만원</div>
                        <div class="stat-change">전체의 31%</div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-header">
                            <span class="stat-title">기타 수익</span>
                            <i data-lucide="credit-card" width="16" height="16" style="color: #94a3b8;"></i>
                        </div>
                        <div class="stat-value">1,700만원</div>
                        <div class="stat-change">후원 + 굿즈 판매</div>
                    </div>
                </div>

                <!-- Tabs -->
                <div class="card">
                    <div class="card-content">
                        <div class="tab-list">
                            <div class="tab active" data-tab="trend">수익 추이</div>
                            <div class="tab" data-tab="breakdown">수익원별 분석</div>
                            <div class="tab" data-tab="transactions">거래 내역</div>
                        </div>

                        <div id="tab-trend" class="tab-content active">
                            <canvas id="revenueTrendChart" height="100"></canvas>
                        </div>

                        <div id="tab-breakdown" class="tab-content">
                            <canvas id="revenueBreakdownChart" height="100"></canvas>
                        </div>

                        <div id="tab-transactions" class="tab-content">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>날짜</th>
                                        <th>수익원</th>
                                        <th>플랫폼</th>
                                        <th>금액</th>
                                        <th>상태</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>2025-10-25</td>
                                        <td>광고 수익</td>
                                        <td>YouTube</td>
                                        <td>450,000원</td>
                                        <td><span class="badge badge-default">정산완료</span></td>
                                    </tr>
                                    <tr>
                                        <td>2025-10-23</td>
                                        <td>협찬</td>
                                        <td>Instagram</td>
                                        <td>1,500,000원</td>
                                        <td><span class="badge badge-secondary">정산대기</span></td>
                                    </tr>
                                    <tr>
                                        <td>2025-10-20</td>
                                        <td>후원</td>
                                        <td>트위치</td>
                                        <td>230,000원</td>
                                        <td><span class="badge badge-default">정산완료</span></td>
                                    </tr>
                                    <tr>
                                        <td>2025-10-18</td>
                                        <td>굿즈 판매</td>
                                        <td>스토어</td>
                                        <td>580,000원</td>
                                        <td><span class="badge badge-default">정산완료</span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Sponsorships Page -->
            <div id="page-sponsorships" class="page-content hidden">
                <div style="display: flex; justify-content: space-between; align-items: start; margin-bottom: 1.5rem;">
                    <div>
                        <h3 style="font-size: 1.25rem; font-weight: 600; margin-bottom: 0.25rem;">협찬 계약 관리</h3>
                        <p style="color: #64748b; font-size: 0.875rem;">브랜드 협찬 및 광고 계약 현황을 관리하세요</p>
                    </div>
                    <button class="btn btn-primary" onclick="openSponsorshipModal()">
                        <i data-lucide="plus" width="16" height="16"></i>
                        새 계약 추가
                    </button>
                </div>

                <!-- Sponsorship Stats -->
                <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4" style="margin-bottom: 1.5rem;">
                    <div class="stat-card">
                        <div class="stat-header">
                            <span class="stat-title">활성 계약</span>
                            <i data-lucide="building-2" width="16" height="16" style="color: #94a3b8;"></i>
                        </div>
                        <div class="stat-value">2건</div>
                        <div class="stat-change">진행중 및 대기중</div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-header">
                            <span class="stat-title">총 계약금액</span>
                            <i data-lucide="dollar-sign" width="16" height="16" style="color: #94a3b8;"></i>
                        </div>
                        <div class="stat-value">350만원</div>
                        <div class="stat-change">활성 계약 기준</div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-header">
                            <span class="stat-title">이번 달 계약</span>
                            <i data-lucide="calendar" width="16" height="16" style="color: #94a3b8;"></i>
                        </div>
                        <div class="stat-value">5건</div>
                        <div class="stat-change">
                            <span class="positive">+25%</span> 전월 대비
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-header">
                            <span class="stat-title">정산 대기</span>
                            <i data-lucide="file-text" width="16" height="16" style="color: #94a3b8;"></i>
                        </div>
                        <div class="stat-value">1건</div>
                        <div class="stat-change">300만원</div>
                    </div>
                </div>

                <!-- Sponsorships List -->
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">전체 협찬 계약 현황</div>
                        <div class="card-description">모든 계약 목록 및 상태</div>
                    </div>
                    <div class="card-content" style="display: flex; flex-direction: column; gap: 1rem;">
                        <div class="card">
                            <div class="card-header">
                                <div style="display: flex; justify-content: space-between; align-items: start;">
                                    <div>
                                        <div class="card-title" style="font-size: 1rem;">브랜드 A - 뷰티 제품</div>
                                        <div class="card-description" style="display: flex; gap: 1rem; margin-top: 0.5rem;">
                                            <span style="display: flex; align-items: center; gap: 0.25rem;">
                                                <i data-lucide="dollar-sign" width="12" height="12"></i>
                                                2,000,000원
                                            </span>
                                            <span style="display: flex; align-items: center; gap: 0.25rem;">
                                                <i data-lucide="calendar" width="12" height="12"></i>
                                                2025-10-15 ~ 2025-11-15
                                            </span>
                                        </div>
                                    </div>
                                    <span class="badge badge-default">진행중</span>
                                </div>
                            </div>
                            <div class="card-content">
                                <div style="display: grid; grid-template-columns: repeat(2, 1fr); gap: 1rem; font-size: 0.875rem; margin-bottom: 1rem;">
                                    <div>
                                        <p style="color: #64748b;">제공 콘텐츠</p>
                                        <p>영상 1개, 포스트 2개</p>
                                    </div>
                                    <div>
                                        <p style="color: #64748b;">정산 상태</p>
                                        <p>선금 입금완료</p>
                                    </div>
                                </div>
                                <div>
                                    <div style="display: flex; justify-content: space-between; font-size: 0.75rem; color: #64748b; margin-bottom: 0.25rem;">
                                        <span>진행률</span>
                                        <span>60%</span>
                                    </div>
                                    <div class="progress-bar">
                                        <div class="progress-fill" style="width: 60%;"></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="card">
                            <div class="card-header">
                                <div style="display: flex; justify-content: space-between; align-items: start;">
                                    <div>
                                        <div class="card-title" style="font-size: 1rem;">브랜드 B - 패션 의류</div>
                                        <div class="card-description" style="display: flex; gap: 1rem; margin-top: 0.5rem;">
                                            <span style="display: flex; align-items: center; gap: 0.25rem;">
                                                <i data-lucide="dollar-sign" width="12" height="12"></i>
                                                1,500,000원
                                            </span>
                                            <span style="display: flex; align-items: center; gap: 0.25rem;">
                                                <i data-lucide="calendar" width="12" height="12"></i>
                                                2025-10-20 ~ 2025-11-20
                                            </span>
                                        </div>
                                    </div>
                                    <span class="badge badge-outline">대기중</span>
                                </div>
                            </div>
                            <div class="card-content">
                                <div style="display: grid; grid-template-columns: repeat(2, 1fr); gap: 1rem; font-size: 0.875rem; margin-bottom: 1rem;">
                                    <div>
                                        <p style="color: #64748b;">제공 콘텐츠</p>
                                        <p>릴스 3개</p>
                                    </div>
                                    <div>
                                        <p style="color: #64748b;">정산 상태</p>
                                        <p>계약서 검토중</p>
                                    </div>
                                </div>
                                <div>
                                    <div style="display: flex; justify-content: space-between; font-size: 0.75rem; color: #64748b; margin-bottom: 0.25rem;">
                                        <span>진행률</span>
                                        <span>30%</span>
                                    </div>
                                    <div class="progress-bar">
                                        <div class="progress-fill" style="width: 30%;"></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="card">
                            <div class="card-header">
                                <div style="display: flex; justify-content: space-between; align-items: start;">
                                    <div>
                                        <div class="card-title" style="font-size: 1rem;">브랜드 C - 전자기기</div>
                                        <div class="card-description" style="display: flex; gap: 1rem; margin-top: 0.5rem;">
                                            <span style="display: flex; align-items: center; gap: 0.25rem;">
                                                <i data-lucide="dollar-sign" width="12" height="12"></i>
                                                3,000,000원
                                            </span>
                                            <span style="display: flex; align-items: center; gap: 0.25rem;">
                                                <i data-lucide="calendar" width="12" height="12"></i>
                                                2025-09-25 ~ 2025-10-25
                                            </span>
                                        </div>
                                    </div>
                                    <span class="badge badge-secondary">정산대기</span>
                                </div>
                            </div>
                            <div class="card-content">
                                <div style="display: grid; grid-template-columns: repeat(2, 1fr); gap: 1rem; font-size: 0.875rem; margin-bottom: 1rem;">
                                    <div>
                                        <p style="color: #64748b;">제공 콘텐츠</p>
                                        <p>리뷰 영상 2개, 숏츠 5개</p>
                                    </div>
                                    <div>
                                        <p style="color: #64748b;">정산 상태</p>
                                        <p>잔금 정산 예정</p>
                                    </div>
                                </div>
                                <div>
                                    <div style="display: flex; justify-content: space-between; font-size: 0.75rem; color: #64748b; margin-bottom: 0.25rem;">
                                        <span>진행률</span>
                                        <span>100%</span>
                                    </div>
                                    <div class="progress-bar">
                                        <div class="progress-fill" style="width: 100%;"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <!-- Schedule Modal -->
    <div id="scheduleModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title">새 콘텐츠 일정 추가</div>
                <div class="modal-description">새로운 콘텐츠 제작 일정을 등록하세요</div>
            </div>
            <form onsubmit="event.preventDefault(); closeScheduleModal();">
                <div class="form-group">
                    <label class="form-label" for="platform">플랫폼</label>
                    <select class="form-select" id="platform">
                        <option value="">플랫폼 선택</option>
                        <option value="youtube">YouTube</option>
                        <option value="instagram">Instagram</option>
                        <option value="tiktok">TikTok</option>
                    </select>
                </div>
                <div class="form-group">
                    <label class="form-label" for="title">콘텐츠 제목</label>
                    <input class="form-input" id="title" type="text" placeholder="제목을 입력하세요">
                </div>
                <div class="grid grid-cols-2" style="gap: 1rem;">
                    <div class="form-group">
                        <label class="form-label" for="date">업로드 날짜</label>
                        <input class="form-input" id="date" type="date">
                    </div>
                    <div class="form-group">
                        <label class="form-label" for="time">업로드 시간</label>
                        <input class="form-input" id="time" type="time">
                    </div>
                </div>
                <div class="form-group">
                    <label class="form-label" for="status">진행 상태</label>
                    <select class="form-select" id="status">
                        <option value="">상태 선택</option>
                        <option value="planning">기획</option>
                        <option value="filming">촬영중</option>
                        <option value="filmed">촬영완료</option>
                        <option value="editing">편집중</option>
                        <option value="uploaded">업로드완료</option>
                    </select>
                </div>
                <div class="form-group">
                    <label class="form-label" for="description">설명</label>
                    <textarea class="form-textarea" id="description" placeholder="콘텐츠 설명 및 메모"></textarea>
                </div>
                <div style="display: flex; gap: 0.5rem;">
                    <button type="submit" class="btn btn-primary" style="flex: 1;">저장</button>
                    <button type="button" class="btn btn-outline" onclick="closeScheduleModal()">취소</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Sponsorship Modal -->
    <div id="sponsorshipModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title">새 협찬 계약 추가</div>
                <div class="modal-description">새로운 브랜드 협찬 계약을 등록하세요</div>
            </div>
            <form onsubmit="event.preventDefault(); closeSponsorshipModal();">
                <div class="form-group">
                    <label class="form-label" for="brand">브랜드명</label>
                    <input class="form-input" id="brand" type="text" placeholder="브랜드 이름을 입력하세요">
                </div>
                <div class="form-group">
                    <label class="form-label" for="amount">계약금액 (원)</label>
                    <input class="form-input" id="amount" type="number" placeholder="1000000">
                </div>
                <div class="grid grid-cols-2" style="gap: 1rem;">
                    <div class="form-group">
                        <label class="form-label" for="startDate">시작일</label>
                        <input class="form-input" id="startDate" type="date">
                    </div>
                    <div class="form-group">
                        <label class="form-label" for="endDate">종료일</label>
                        <input class="form-input" id="endDate" type="date">
                    </div>
                </div>
                <div class="form-group">
                    <label class="form-label" for="contractStatus">계약 상태</label>
                    <select class="form-select" id="contractStatus">
                        <option value="">상태 선택</option>
                        <option value="negotiating">협상중</option>
                        <option value="pending">대기중</option>
                        <option value="active">진행중</option>
                        <option value="waiting">정산대기</option>
                        <option value="completed">완료</option>
                    </select>
                </div>
                <div class="form-group">
                    <label class="form-label" for="deliverables">제공 콘텐츠</label>
                    <textarea class="form-textarea" id="deliverables" placeholder="영상 1개, 포스트 2개 등"></textarea>
                </div>
                <div class="form-group">
                    <label class="form-label" for="contact">담당자 연락처</label>
                    <input class="form-input" id="contact" type="email" placeholder="email@example.com">
                </div>
                <div style="display: flex; gap: 0.5rem;">
                    <button type="submit" class="btn btn-primary" style="flex: 1;">저장</button>
                    <button type="button" class="btn btn-outline" onclick="closeSponsorshipModal()">취소</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        // Initialize Lucide Icons
        lucide.createIcons();

        // Navigation
        const menuItems = document.querySelectorAll('.menu-item');
        const pageTitle = document.getElementById('pageTitle');
        const pages = {
            'dashboard': { element: document.getElementById('page-dashboard'), title: '대시보드' },
            'schedule': { element: document.getElementById('page-schedule'), title: '콘텐츠 일정' },
            'revenue': { element: document.getElementById('page-revenue'), title: '수익 관리' },
            'sponsorships': { element: document.getElementById('page-sponsorships'), title: '협찬 계약' }
        };

        menuItems.forEach(item => {
            item.addEventListener('click', () => {
                const pageName = item.getAttribute('data-page');
                
                // Update active menu item
                menuItems.forEach(mi => mi.classList.remove('active'));
                item.classList.add('active');

                // Update page title
                pageTitle.textContent = pages[pageName].title;

                // Show selected page
                Object.values(pages).forEach(page => page.element.classList.add('hidden'));
                pages[pageName].element.classList.remove('hidden');

                // Close sidebar on mobile
                if (window.innerWidth < 768) {
                    document.getElementById('sidebar').classList.remove('show');
                }

                // Re-render charts if on revenue page
                if (pageName === 'revenue') {
                    setTimeout(renderRevenueCharts, 100);
                }
            });
        });

        // Menu Toggle
        const menuToggle = document.getElementById('menuToggle');
        const sidebar = document.getElementById('sidebar');
        
        menuToggle.addEventListener('click', () => {
            sidebar.classList.toggle('show');
        });

        // Tabs
        const tabs = document.querySelectorAll('.tab');
        tabs.forEach(tab => {
            tab.addEventListener('click', () => {
                const tabName = tab.getAttribute('data-tab');
                const tabList = tab.parentElement;
                const allTabs = tabList.querySelectorAll('.tab');
                const allContents = tab.closest('.card-content').querySelectorAll('.tab-content');

                allTabs.forEach(t => t.classList.remove('active'));
                tab.classList.add('active');

                allContents.forEach(content => content.classList.remove('active'));
                document.getElementById('tab-' + tabName).classList.add('active');
            });
        });

        // Modal Functions
        function openScheduleModal() {
            document.getElementById('scheduleModal').classList.add('show');
        }

        function closeScheduleModal() {
            document.getElementById('scheduleModal').classList.remove('show');
        }

        function openSponsorshipModal() {
            document.getElementById('sponsorshipModal').classList.add('show');
        }

        function closeSponsorshipModal() {
            document.getElementById('sponsorshipModal').classList.remove('show');
        }

        // Close modals when clicking outside
        document.querySelectorAll('.modal').forEach(modal => {
            modal.addEventListener('click', (e) => {
                if (e.target === modal) {
                    modal.classList.remove('show');
                }
            });
        });

        // Charts
        function renderRevenueChart() {
            const ctx = document.getElementById('revenueChart').getContext('2d');
            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ['6월', '7월', '8월', '9월', '10월'],
                    datasets: [
                        {
                            label: '광고',
                            data: [1200, 1500, 1800, 2200, 2800],
                            backgroundColor: '#8b5cf6'
                        },
                        {
                            label: '협찬',
                            data: [800, 1200, 1000, 1500, 2000],
                            backgroundColor: '#06b6d4'
                        },
                        {
                            label: '후원',
                            data: [400, 500, 600, 700, 900],
                            backgroundColor: '#10b981'
                        },
                        {
                            label: '굿즈',
                            data: [300, 450, 550, 650, 800],
                            backgroundColor: '#f59e0b'
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                        x: { stacked: true },
                        y: { stacked: true }
                    }
                }
            });
        }

        function renderPlatformChart() {
            const ctx = document.getElementById('platformChart').getContext('2d');
            new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: ['유튜브', '인스타그램', '틱톡'],
                    datasets: [{
                        data: [45, 30, 25],
                        backgroundColor: ['#FF0000', '#E4405F', '#000000']
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false
                }
            });
        }

        function renderEngagementChart() {
            const ctx = document.getElementById('engagementChart').getContext('2d');
            new Chart(ctx, {
                type: 'line',
                data: {
                    labels: ['10/20', '10/21', '10/22', '10/23', '10/24', '10/25', '10/26'],
                    datasets: [
                        {
                            label: '조회수',
                            data: [15000, 18000, 22000, 19000, 25000, 28000, 32000],
                            borderColor: '#8b5cf6',
                            tension: 0.4,
                            yAxisID: 'y'
                        },
                        {
                            label: '참여율 (%)',
                            data: [5.2, 6.1, 7.3, 6.5, 8.2, 9.1, 10.5],
                            borderColor: '#06b6d4',
                            tension: 0.4,
                            yAxisID: 'y1'
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                        y: {
                            type: 'linear',
                            position: 'left'
                        },
                        y1: {
                            type: 'linear',
                            position: 'right',
                            grid: {
                                drawOnChartArea: false
                            }
                        }
                    }
                }
            });
        }

        function renderRevenueCharts() {
            // Revenue Trend Chart
            const trendCtx = document.getElementById('revenueTrendChart').getContext('2d');
            new Chart(trendCtx, {
                type: 'line',
                data: {
                    labels: ['4월', '5월', '6월', '7월', '8월', '9월', '10월'],
                    datasets: [
                        {
                            label: '광고',
                            data: [1000, 1100, 1200, 1500, 1800, 2200, 2800],
                            borderColor: '#8b5cf6',
                            tension: 0.4
                        },
                        {
                            label: '협찬',
                            data: [600, 700, 800, 1200, 1000, 1500, 2000],
                            borderColor: '#06b6d4',
                            tension: 0.4
                        },
                        {
                            label: '후원',
                            data: [300, 350, 400, 500, 600, 700, 900],
                            borderColor: '#10b981',
                            tension: 0.4
                        },
                        {
                            label: '굿즈',
                            data: [200, 250, 300, 450, 550, 650, 800],
                            borderColor: '#f59e0b',
                            tension: 0.4
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false
                }
            });

            // Revenue Breakdown Chart
            const breakdownCtx = document.getElementById('revenueBreakdownChart').getContext('2d');
            new Chart(breakdownCtx, {
                type: 'bar',
                data: {
                    labels: ['광고', '협찬', '후원', '굿즈'],
                    datasets: [{
                        label: '금액 (만원)',
                        data: [2800, 2000, 900, 800],
                        backgroundColor: '#8b5cf6'
                    }]
                },
                options: {
                    indexAxis: 'y',
                    responsive: true,
                    maintainAspectRatio: false
                }
            });
        }

        // Initialize Charts
        window.addEventListener('load', () => {
            renderRevenueChart();
            renderPlatformChart();
            renderEngagementChart();
            lucide.createIcons();
        });
    </script>
</body>
</html>
