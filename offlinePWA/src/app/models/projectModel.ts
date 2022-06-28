/* tslint:disable:no-empty-interface */
import {User} from './userModel';

export class BasicCatalog {
  public id: number;
  public code: string;
  public description: string;
  public state?: string;

}

export class ProjectImplementer extends BasicCatalog {
}

export class RightGroup extends BasicCatalog {

}

export class Objetive extends BasicCatalog {
  rightGroup?: RightGroup;
}

export class Output extends BasicCatalog {
  objetive?: Objetive;
}

export class PerformanceIndicator {
  id: number;
  code: string;
  description: string;
  definition: string;
  definitionDirectImplementation: string;
  indicatorType: string;
  state: string;
  output: Output;
  measureType: string;
  percentageType?: string;
  numerator?: string;
  denominator?: string;
  directImplementationArea: DirectImplementationArea;
  periodicity: string;

}

export class Period {
  id: number;
  state: string;
  year: number;
}

export class ProjectResumeWeb {
  id: number;
  code: string;
  name: string;
  projectImplementer?: ProjectImplementer;
  progressPercentaje: number;
  reportedProgress: number;
  situations: Situation[];
  lastReportedMonth: string;
  target: number;
  state: string;
}

export class PeriodResume extends Period {
  numberOfProjects: number;
  numberOfAsignedIndicators: number;
}

export class PeriodPerformanceIndicatorAssigment {
  id: number;
  period: Period;
  performanceIndicator: PerformanceIndicator;
  state: string;
  measureType: string;
  indicatorType: string;
  dissagregationAssigments: DissagregationAssigment[];
}

export class DissagregationAssigment {
  id: number;
  disaggregationType: string;
  state: string;
}

export class Situation extends BasicCatalog {
}

export class Project {
  id: number;
  code: string;
  name: string;
  state: string;
  period: Period;
  projectImplementer: ProjectImplementer;
  situations: Situation[];
  projectLocations?: ProjectLocation[];
  q1OpenToEdition?: boolean;
  q2OpenToEdition?: boolean;
  q3OpenToEdition?: boolean;
  q4OpenToEdition?: boolean;

}

export class QuarterProjectState {
  proyectId: number;
  q1OpenToEdition: boolean;
  q2OpenToEdition: boolean;
  q3OpenToEdition: boolean;
  q4OpenToEdition: boolean;
}

export class Canton extends BasicCatalog {
  provincia?: Provincia;
}

export class Provincia extends BasicCatalog {
}

export class GeneralIndicator {
  id: number;
  parent: boolean;
  description: string;
  calculated: boolean;
  dissagregationAssigments: DissagregationAssigment[];
  nationalityType: string;
  measureType: string;
  target: number;
  totalExecution: number;
  executionPercentage: number;
  state: string;
  period: Period;
}

export class IndicatorExecution {
  id: number;
  attachmentDescription: string;
  project: Project;
  performanceIndicator: PerformanceIndicator;
  output: Output;
  generalIndicator: GeneralIndicator;
  generalIndicatorCalculated: boolean;
  state: string;
  dissagregationAssigments: DissagregationAssigment[];
  nationalityType: string;
  target: number;
  totalExecution: number;
  executionPercentage: number;
  indicatorType: string;
  indicatorExecutionLocationAssigment: IndicatorExecutionLocationAssigment[];
  situation: Situation;
  measureType: string;
  quarters?: Quarter[];
  assignedUser?: User;
  assignedUserBackup?: User;
  reportingOffice?: Office;
  isDirectImplementation?: boolean;
  period?: Period;
  directImplementationDescription?: string;
}

export class Office extends BasicCatalog {
}

export class DirectImplementationArea extends BasicCatalog {
}

export class ProjectLocation {
  id: number;
  canton: Canton;
  state: string;
}

export class IndicatorExecutionLocationAssigment {
  id: number;
  location: Canton;
  state: string;
}

export class ProjectResume {
  id: number;
  code: string;
  name: string;
  projectImplementer?: ProjectImplementer;
  implementationRate: number;
  implementationAmount: number;
  situations: Situation[];
  lastReportedMonth: string;
  target: number;
  periodo: number;
  codigoSocio: string;
  fechaInicioReporte: string;
  fechaFinReporte: string;

}

export class Quarter {
  id: number;
  quarterNumber: string;
  commentary: string;
  totalExecution?: number;
}

export class QuarterValues extends Quarter {
  ageIndicatorValues: IndicatorValue[];
  diversityIndicatorValues: IndicatorValue[];
  genderIndicatorValues: IndicatorValue[];
  locationIndicatorValues: IndicatorValue[];
  nationalityIndicatorValues: IndicatorValue[];
  noneIndicatorValues: IndicatorValue[];
  ageLocationIndicatorValues: IndicatorValue[];
  genderLocationIndicatorValues: IndicatorValue[];
  genderNationalityIndicatorValues: IndicatorValue[];
  genderAgeLocationIndicatorValues: IndicatorValue[];
  percentageNoneIndicatorValues: IndicatorValue[];
  immigrationStatusNationalityIndicatorValues: IndicatorValue[];
  immigrationStatusIndicatorValues: IndicatorValue[];
}

export class IndicatorValue {
  id: number;
  month: string;
  gender: string;
  ageGroup: string;
  nationality: string;
  location: Canton;
  diversityType: string;
  immigrationStatusType: string;
  value: number;
  numeratorValue: number;
  denominatorValue: number;
  dissaggregationType: string;

}

export class IndicatorExecutionSummary {
  projectId: number;
  indicatorExecutionId: number;
  indicatorDescription: string;
  month: string;
  value: number;
}

export class DirectImplementationAreaResume extends BasicCatalog {
  numberOfIndicator: number;
}

export class FileAttachment {
  id: number;
  description: string;
  fileName: string;
  path: string;
  user: User;
  dateTime: Date;
  indicatorExecutionId: number;
  quarterId: number;
  projectId: number;
  file: string;

}

/// ****************************old***************************//


export interface IndicadorResumen extends BasicCatalog {

}

export interface TipoDesagregacion extends BasicCatalog {

}

export interface FocusIndicator extends BasicCatalog {

}

export interface GeneralIndicatorResumen {
  id: number;
  tipoIndicador: string;
  focusIndicator: IndicadorResumen;
  attachmentIndicator: string;
  target: number;
  tipoDesagregacion: TipoDesagregacion;
  quarter1: number;
  quarter2: number;
  quarter3: number;
  quarter4: number;
  total: number;

}

export interface IndicatorResumen {
  id: number;
  rightGroup: RightGroup;
  objetive: Objetive;
  output: Output;
  tipoIndicador: string;
  attachmentIndicator: string;
  focusIndicator: FocusIndicator;
  target: number;
  tipoDesagregacion: TipoDesagregacion;
  quarter1: number;
  quarter2: number;
  quarter3: number;
  quarter4: number;
  total: number;
  porcentajeAvance: number;

}

export interface Month {
  id: number;
  name: string;
}

export enum Gender {
  MALE = 'MALE',
  FEMALE = 'FEMALE',
  OTHER = 'OTHER'
}


export enum AgeGroup {
  CHILD = 'CHILD',
  TEEN = 'TEEN',
  YOUNG_ADULT = 'YOUNG_ADULT',
  ADULT = 'ADULT',
  ELDERLY = 'ELDERLY'
}

export interface Implementer {
  id: number;
  code: string;
  description: string;
  state: string;
}

export interface IndicatorSetUp {
  id: number;
  rightGroup: RightGroup;
  objetive: Objetive;
  output: Output;
  tipoIndicador: string;
  attachmentIndicator: string;
  focusIndicator: FocusIndicator;
  target: number;
  tipoDesagregacion: TipoDesagregacion;
}



















