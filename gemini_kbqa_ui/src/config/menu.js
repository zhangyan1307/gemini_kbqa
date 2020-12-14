// 菜单配置
// headerMenuConfig：头部导航配置
// asideMenuConfig：侧边导航配置

const headerMenuConfig = [];

const asideMenuConfig = [
  {
    path: '/dashboard',
    name: 'Dashboard',
    icon: 'el-icon-menu',
    children: [{ path: '/analysis', name: '分析页', id: 'Menu_l5wp0' }],
    id: 'Menu_t6jdn',
  },
  {
    path: '/kbqaAdmin',
    name: '知识库管理',
    icon: 'el-icon-date',
    children: [
      { path: '/commonKb', name: '问答知识库', id: 'Menu_101qn' },
      { path: '/customerKb', name: '内部知识库', id: 'Menu_2unqj' },
    ],
    id: 'Menu_srxy6',
  },
  {
    path: '/kbqaBot',
    name: '机器人管理',
    icon: 'el-icon-edit-outline',
    children: [
      { path: '/kbqaBotAdmin', name: '机器人列表管理', id: 'Menu_kgtp5' },
      { path: '/kbqaBotTest', name: '智能机器人测试', id: 'Menu_3e0t5' },
    ],
    id: 'Menu_rcgpv',
  },
];

export { headerMenuConfig, asideMenuConfig };
