import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { SurvivorDetailComponent } from './survivor-detail.component';

describe('Survivor Management Detail Component', () => {
  let comp: SurvivorDetailComponent;
  let fixture: ComponentFixture<SurvivorDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SurvivorDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./survivor-detail.component').then(m => m.SurvivorDetailComponent),
              resolve: { survivor: () => of({ id: 19623 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(SurvivorDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SurvivorDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load survivor on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', SurvivorDetailComponent);

      // THEN
      expect(instance.survivor()).toEqual(expect.objectContaining({ id: 19623 }));
    });
  });

  describe('PreviousState', () => {
    it('Should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});
