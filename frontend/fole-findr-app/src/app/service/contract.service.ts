import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Contract} from "../model/contract";
import {AppSettings} from "../constants/app-settings";
import {AppUrl} from "../constants/app-url";
import {ContractInsert} from "../model/contract-insert";

@Injectable({
  providedIn: 'root'
})
export class ContractService {

  constructor(private readonly httpClient: HttpClient) {
  }

  public getContract() {

  }

  public getContractsByStrudent(id: number): Observable<Contract[]> {
    return this.httpClient.get<Contract[]>(AppSettings.BASE_URL + AppUrl.CONTRACT_URL + '/' + id);
  }

  public saveContract(contract: ContractInsert): Observable<void> {
    return this.httpClient.post<void>(AppSettings.BASE_URL + AppUrl.CONTRACT_URL, contract);
  }

  public generatePDF(id:number): Observable<any> {
    return this.httpClient.get<any>(AppSettings.BASE_URL + AppUrl.PDF_URL + '/' + id);
  }
}
