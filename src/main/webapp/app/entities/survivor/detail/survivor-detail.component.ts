import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { ISurvivor } from '../survivor.model';

@Component({
  selector: 'jhi-survivor-detail',
  templateUrl: './survivor-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class SurvivorDetailComponent {
  survivor = input<ISurvivor | null>(null);

  previousState(): void {
    window.history.back();
  }
}
