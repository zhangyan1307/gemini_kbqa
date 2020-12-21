import HeaderAsideLayout from "@/layouts/HeaderAsideLayout";
import NotFound from "@/pages/NotFound";
import CustomerKb from "@/pages/CustomerKb";
import Login from "@/pages/Login/Login";
const routerConfig = [
  {
    path: "/",
    redirect: "/login",
  },
  {
    path: "/login",
    component: Login,
  },
  {
    path: "/kbqaAdmin",
    component: HeaderAsideLayout,
    children: [
      {
        path: "/kbqaAdmin/commonKb",
        component: CustomerKb,
      },
      { path: "/kbqaAdmin/customerKb", component: CustomerKb },
    ],
  },
  {
    path: "/form",
    component: HeaderAsideLayout,
    children: [
      { path: "/form/basic", component: NotFound },
      { path: "/form/signup", component: NotFound },
    ],
  },
  {
    path: "/charts",
    component: HeaderAsideLayout,
    children: [
      { path: "/charts/line", component: NotFound },
      { path: "/charts/histogram", component: NotFound },
      { path: "/charts/bar", component: NotFound },
    ],
  },
  {
    path: "/profile",
    component: HeaderAsideLayout,
    children: [
      { path: "/profile/success", component: NotFound },
      { path: "/profile/fail", component: NotFound },
    ],
  },
  {
    path: "/result",
    component: HeaderAsideLayout,
    children: [
      { path: "/result/success", component: NotFound },
      { path: "/result/fail", component: NotFound },
    ],
  },
  {
    path: "/dashboard",
    component: HeaderAsideLayout,
    // redirect: '/dashboard/analysis',
    children: [
      { path: "/dashboard/analysis", component: NotFound },
      { path: "/dashboard/monitor", component: NotFound },
      { path: "/dashboard/workplace", component: NotFound },
    ],
  },
  { path: "*", component: NotFound },
];

export default routerConfig;
