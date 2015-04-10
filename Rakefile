task 'A1' => 'A1.pdf'
task 'A2' => 'A2/q1.pdf'
rule '.pdf' => '.md' do |t|
  sh "pandoc #{t.source} -o #{t.name}"
end
