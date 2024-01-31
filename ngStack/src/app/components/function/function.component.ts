import { AuthService } from './../../services/auth.service';
import { CommonModule, } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FunctionService } from '../../services/function.service';
import { Function } from '../../models/function';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';

@Component({
  selector: 'app-function',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './function.component.html',
  styleUrl: './function.component.css'
})
export class FunctionComponent implements OnInit {

  function: Function = new Function();
  functions: Function[] = [];
  nodeId: number = 0;


  constructor(private auth: AuthService, private funServ: FunctionService, private router: Router, private route: ActivatedRoute){}

  ngOnInit(){
    this.route.paramMap.subscribe(
      (params: ParamMap)=> {
        this.nodeId = parseInt(params.get('id')!);
      });
    this.loadData(this.nodeId);
  }

  loadData(nodeid: number){
    this.funServ.getFunctions(nodeid).subscribe(
      {
        next: (functs)=>{
          this.functions = functs;
          // this.fixTimes();
        },
        error: (errors)=>{
          console.log(errors)
        }
      }
    );
  }

  createNew(){
    this.router.navigateByUrl("nodes/" + this.nodeId + "/function/create")
  }


}
