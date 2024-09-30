import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusProviderComponent } from './bus-provider.component';

describe('BusProviderComponent', () => {
  let component: BusProviderComponent;
  let fixture: ComponentFixture<BusProviderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BusProviderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BusProviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
