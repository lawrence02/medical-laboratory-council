import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ISurvivor } from '../survivor.model';
import { SurvivorService } from '../service/survivor.service';

@Component({
  templateUrl: './survivor-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class SurvivorDeleteDialogComponent {
  survivor?: ISurvivor;

  protected survivorService = inject(SurvivorService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.survivorService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
