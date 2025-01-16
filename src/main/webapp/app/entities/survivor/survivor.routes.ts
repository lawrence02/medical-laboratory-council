import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import SurvivorResolve from './route/survivor-routing-resolve.service';

const survivorRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/survivor.component').then(m => m.SurvivorComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/survivor-detail.component').then(m => m.SurvivorDetailComponent),
    resolve: {
      survivor: SurvivorResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/survivor-update.component').then(m => m.SurvivorUpdateComponent),
    resolve: {
      survivor: SurvivorResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/survivor-update.component').then(m => m.SurvivorUpdateComponent),
    resolve: {
      survivor: SurvivorResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default survivorRoute;
