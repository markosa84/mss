import { DoctorSelector } from "./DoctorSelector";
import { SectionHeader } from "./SectionHeader";

export function DoctorSelectorSection() {
  return (
    <div>
      <SectionHeader>1. Please select doctor(s)</SectionHeader>
      <DoctorSelector />
    </div>
  );
}
