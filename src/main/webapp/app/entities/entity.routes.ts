import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'medicalLaboratoryCouncilApp.adminAuthority.home.title' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'survivor',
    data: { pageTitle: 'medicalLaboratoryCouncilApp.survivor.home.title' },
    loadChildren: () => import('./survivor/survivor.routes'),
  },
  {
    path: 'resource',
    data: { pageTitle: 'medicalLaboratoryCouncilApp.resource.home.title' },
    loadChildren: () => import('./resource/resource.routes'),
  },
  {
    path: 'practitioner',
    data: { pageTitle: 'medicalLaboratoryCouncilApp.practitioner.home.title' },
    loadChildren: () => import('./practitioner/practitioner.routes'),
  },
  {
    path: 'qualification',
    data: { pageTitle: 'medicalLaboratoryCouncilApp.qualification.home.title' },
    loadChildren: () => import('./qualification/qualification.routes'),
  },
  {
    path: 'payment',
    data: { pageTitle: 'medicalLaboratoryCouncilApp.payment.home.title' },
    loadChildren: () => import('./payment/payment.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
