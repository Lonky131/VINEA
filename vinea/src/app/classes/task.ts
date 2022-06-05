export class task {
  id : string;
  name : string;
  assignee : string | null;
  created : string;
  due : string | null;
  followUp : string | null;
  delegationState : string | null;
  description : string | null;
  executionId : number | null;
  owner : string | null;
  parentTaskId : number | null;
  priority : number | null;
  processDefinitionId : number | null;
  processInstanceId : number | null;
  taskDefinitionKey : string | null;
  caseExecutionId : number | null;
  caseInstanceId : number | null;
  caseDefinitionId : number | null;
  suspended : boolean;
  formKey : string | null;
  camundaFormRef : string | null;
  tenantId : number | null;
}

export class tableTask {
  taskId : string;
  firstName : string;
  lastName : string;
  idCardNumber : string;
}

export class userTableTask {
  taskId : string;
  firstName : string;
  lastName : string;
  status : string;
}
